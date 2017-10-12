package org.opentosca.toscana.core.api;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opentosca.toscana.core.api.dummy.DummyCsar;
import org.opentosca.toscana.core.api.dummy.DummyPlatformProvider;
import org.opentosca.toscana.core.api.mocks.MockCsarService;
import org.opentosca.toscana.core.csar.CsarService;
import org.opentosca.toscana.core.transformation.Platform;
import org.opentosca.toscana.core.util.PlatformProvider;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.MessageDigest;
import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.opentosca.toscana.core.api.utils.HALRelationUtils.validateRelations;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(
	classMode = ClassMode.AFTER_EACH_TEST_METHOD
)
public class CsarControllerTest {

	private static Platform[] platforms = {
		new Platform("kubernetes", "Kubernetes", new HashSet<>()),
		new Platform("open-stack", "OpenStack", new HashSet<>()),
		new Platform("cloud-foundry", "CloudFoundry", new HashSet<>())
	};

	public static Map<String, String> relations;

	static {
		relations = new HashMap<>();
		relations.put("self", "http://localhost/csars/%s");
		relations.put("transformations", "http://localhost/csars/%s/transformations/");
	}

	private PlatformProvider provider;

	private CsarService service;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		service = new MockCsarService();
		provider = new DummyPlatformProvider(Arrays.asList(platforms));
		CsarController controller = new CsarController();
		controller.platformProvider = provider;
		controller.csarService = service;
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void listCsars() throws Exception {
		ResultActions resultActions = mvc.perform(
			get("/csars").accept("application/hal+json")
		).andDo(print()).andExpect(status().is2xxSuccessful());
		resultActions.andExpect(jsonPath("$.links[0].rel").value("self"));
		resultActions.andExpect(jsonPath("$.links[0].href").value("http://localhost/csars/"));
		resultActions.andExpect(jsonPath("$.content").exists());
		resultActions.andExpect(jsonPath("$.content").isArray());
		resultActions.andExpect(jsonPath("$.content[2]").doesNotExist());
		resultActions.andReturn();
	}

	@Test
	public void uploadTest() throws Exception {
		//Generate 10 MiB of "Random" (seeded) data
		byte[] data = new byte[(int) (Math.pow(2, 20) * 10)];
		Random rnd = new Random(12345678);
		rnd.nextBytes(data);
		// Get the sha hash of the data
		byte[] hash = MessageDigest.getInstance("SHA-256").digest(data);

		MockMultipartFile mockMultipartFile = new MockMultipartFile(
			"file",
			"null",
			MediaType.APPLICATION_OCTET_STREAM_VALUE,
			data
		);

		MockMultipartHttpServletRequestBuilder builder = fileUpload("/csars/rnd");
		builder.with(request -> {
			request.setMethod("PUT");
			return request;
		});
		builder = (MockMultipartHttpServletRequestBuilder) builder.file(mockMultipartFile)
			.contentType(MediaType.MULTIPART_FORM_DATA);

		ResultActions resultActions = mvc.perform(
			builder
		).andDo(print()).andExpect(status().is2xxSuccessful());
		resultActions.andReturn();

		byte[] hashUpload = MessageDigest
			.getInstance("SHA-256")
			.digest(((DummyCsar) service.getCsar("rnd")).getData());
		assertTrue(hashUpload.length == hash.length);
		for (int i = 0; i < hash.length; i++) {
			assertTrue(hash[i] == hashUpload[i]);
		}
	}

	@Test
	public void uploadTestArchiveAlreadyExists() throws Exception {
		//Generate 10 KiB of "Random" (seeded) data
		byte[] data = new byte[(int) (Math.pow(2, 10) * 10)];
		Random rnd = new Random(12345678);
		rnd.nextBytes(data);

		MockMultipartFile mockMultipartFile = new MockMultipartFile(
			"file",
			"null",
			MediaType.APPLICATION_OCTET_STREAM_VALUE,
			data
		);

		MockMultipartHttpServletRequestBuilder builder = fileUpload("/csars/apache");
		builder.with(request -> {
			request.setMethod("PUT");
			return request;
		});
		builder = (MockMultipartHttpServletRequestBuilder) builder.file(mockMultipartFile)
			.contentType(MediaType.MULTIPART_FORM_DATA);

		ResultActions resultActions = mvc.perform(
			builder
		).andDo(print()).andExpect(status().is(400));
		resultActions.andReturn();
	}

	@Test
	public void csarDetails() throws Exception {
		for (String name : MockCsarService.names) {
			ResultActions resultActions = mvc.perform(
				get("/csars/" + name).accept("application/hal+json")
			).andDo(print()).andExpect(status().is2xxSuccessful());
			resultActions.andExpect(jsonPath("$.name").value(name));
			resultActions.andExpect(jsonPath("$.links").isArray());
			resultActions.andExpect(jsonPath("$.links[" + relations.size() + "]").doesNotExist());

			//Validate String result
			MvcResult result = resultActions.andReturn();
			JSONObject object = new JSONObject(result.getResponse().getContentAsString());
			validateRelations(object.getJSONArray("links"), relations, name);
		}
	}

	@Test
	public void csarDetails404() throws Exception {
		ResultActions resultActions = mvc.perform(
			get("/csars/notacsar").accept("application/hal+json")
		).andDo(print()).andExpect(status().is(404));
	}
}