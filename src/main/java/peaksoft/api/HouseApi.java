package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Agency;
import peaksoft.model.House;
import peaksoft.service.HouseSer;

@Controller
@RequestMapping("/houses/{agencyId}")
@RequiredArgsConstructor
public class HouseApi {
    private final HouseSer houseSer;



    @GetMapping
    public String getAllHouse(@RequestParam(value = "word",required = false) @PathVariable Long agencyId, String word, Model model) {
        model.addAttribute("houses", houseSer.getAllHouses(agencyId,word));
        model.addAttribute("word", word);
        model.addAttribute( agencyId);
        return "house/allHouse";
    }
    @GetMapping("/new")
    public String create(@PathVariable Long agencyId, Model model) {
        model.addAttribute("house", new House());
        return "house/newHouse";
    }

    @PostMapping("/saveHouse")
    public String saveAgency(@PathVariable Long agencyId, @ModelAttribute("house") House house) {
       houseSer.saveHouse(agencyId,house);
        return "redirect:/houses/"+agencyId;
    }
    @GetMapping("/{id}")
    public String getHouseById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("houses", houseSer.getHouseById(id));
        return "house/getByIdHouse";
    }
    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("edit", houseSer.getHouseById(id));;
        return "house/updateHouse";
    }
    @PostMapping("/update/{id}")
    public String saveUpdate(@ModelAttribute("edit") House house,
                             @PathVariable("id") Long id) {
        houseSer.updateHouseById(id, house);
        return "redirect:/houses";
    }
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
      houseSer.deleteHouseById(id);
        return "redirect:/houses";
    }



}
