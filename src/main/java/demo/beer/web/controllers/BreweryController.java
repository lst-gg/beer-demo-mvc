
package demo.beer.web.controllers;

import demo.beer.domain.Brewery;
import demo.beer.security.perms.BreweryReadPermission;
import demo.beer.services.BreweryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/brewery")
@Controller
public class BreweryController {

    private final BreweryService breweryService;

    @BreweryReadPermission
    @GetMapping({"/breweries", "/breweries/index", "/breweries/index.html", "/breweries.html"})
    public String listBreweries(Model model) {
        model.addAttribute("breweries", breweryService.getAllBreweries());
        return "breweries/index";
    }

    @BreweryReadPermission
    @GetMapping("/api/v1/breweries")
    public @ResponseBody List<Brewery> getBreweriesJson(){
        return breweryService.getAllBreweries();
    }
}
