package hovorkap.idtoolbox

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiConfiguration {

    @Bean
    fun openAPI() = OpenAPI().info(Info()
            .title("ID Toolbox API")
            .version("1.0"))
}
