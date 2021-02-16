package hovorkap.idtoolbox.api

import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import java.io.IOException

@RestController
class ApiController {

    @RequestMapping("/")
    @Throws(IOException::class)
    fun redirectToDocumentation(model: ModelMap): ModelAndView {
        return ModelAndView("redirect:/swagger-ui/index.html", model)
    }
}
