package com.co.andres_rico_verboshttp.controlador;
import com.co.andres_rico_verboshttp.model.GroceryItem;
import com.co.andres_rico_verboshttp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

 @RestController
public class itemController {
    @Autowired
    ItemService itemService;

    // 1. metodo de ejemplo para verbo GET
    @GetMapping("/getAll")
    public String getAll(){
        return itemService.getAll().toString();
    }

    // 2. metodo de ejemplo para verbo POST
    @PostMapping("/insert")
    public String insert(@RequestBody GroceryItem groceryItem){
        return itemService.insert(groceryItem);
    }

    // 3. metodo de ejemplo para verbo UPDATE
    @PutMapping("/update")
    public String update(@RequestBody GroceryItem groceryItem){
        return itemService.update(groceryItem);
    }

    // 4. metodo de ejemplo para verbo DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") String id) {
        return itemService.delete(id);
    }

    // 5. metodo de ejemplo para verbo PATH
    @PatchMapping("/updateData/{id}")
    public String updateData(@PathVariable String id, @RequestBody GroceryItem groceryItem){
        return itemService.updateData(id, groceryItem);
    }

    // 6. metodo de ejemplo para verbo HEAD
    @RequestMapping(value = "/getAll", method = RequestMethod.HEAD)
    public ResponseEntity<?> handleHeadRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Custom-Header", "This is a custom header");
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


 }
