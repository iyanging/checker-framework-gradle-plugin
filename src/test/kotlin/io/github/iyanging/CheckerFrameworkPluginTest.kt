package io.github.iyanging

import kotlin.test.Test
import kotlin.test.assertNotNull
import org.gradle.testfixtures.ProjectBuilder

class CheckerFrameworkPluginTest {
    @Test
    fun `plugin registers task`() {
        // Create a test project and apply the plugin
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("io.github.iyanging.checker-framework")

        // Verify the result
        assertNotNull(project.tasks.findByName("hello"))
    }
}
