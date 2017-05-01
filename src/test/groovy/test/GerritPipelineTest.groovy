package test;

import org.junit.Before
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers

import static groovy.util.GroovyTestCase.assertEquals

import stubs.Docker
import com.lesfurets.jenkins.unit.BasePipelineTest
import static com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration.library
import static com.lesfurets.jenkins.unit.global.lib.GitSource.gitSource

import groovy.lang.Script;

public class GerritPipelineTest extends BasePipelineTest {

	@Override
	@Before
	void setUp() throws Exception {
		super.setUp()
		getBinding().setVariable('docker', new Docker())
	}

	@Test
	void shouldRunSuccessScenario() throws Exception {
		def library = library()
				.name('commons')
				.retriever(gitSource('https://github.com/attiand/pipeline-lib.git'))
				.targetPath(System.getProperty('java.io.tmpdir'))
				.defaultVersion('master')
				.build()

		helper.registerSharedLibrary(library)

		Script script = loadScript("job/Jenkinsfile")

		printCallStack()

		assertJobStatusSuccess()
	}
}
