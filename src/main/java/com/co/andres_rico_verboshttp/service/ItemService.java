package com.co.andres_rico_verboshttp.service;

import com.co.andres_rico_verboshttp.model.GroceryItem;
import com.co.andres_rico_verboshttp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // Obtener todos los ítems
    public List<GroceryItem> getAll() {
        return itemRepository.findAll();
    }

    // Insertar un nuevo ítem
    public String insert(GroceryItem groceryItem) {
        itemRepository.save(groceryItem);
        return "---ITEM INSERTADO---\n" + groceryItem.toString();
    }

    // Actualizar un ítem existente (reemplazo completo)
    public String update(GroceryItem groceryItem) {
        Optional<GroceryItem> itemOptional = itemRepository.findById(groceryItem.getId());
        if (itemOptional.isPresent()) {
            GroceryItem existingItem = itemOptional.get();
            existingItem.setName(groceryItem.getName());
            existingItem.setQuantity(groceryItem.getQuantity());
            existingItem.setCategory(groceryItem.getCategory());
            itemRepository.save(existingItem);
            return "---ITEM ACTUALIZADO---\n" + existingItem.toString();
        } else {
            return "---ITEM NO ENCONTRADO---";
        }
    }

    // Eliminar un ítem por ID
    public String delete(String id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return "---ITEM ELIMINADO---";
        } else {
            return "---ITEM NO ENCONTRADO---";
        }
    }

    // Actualización parcial de un ítem (específico para ciertos campos)
    public String partialUpdate(String id, GroceryItem groceryItem) {
        Optional<GroceryItem> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            GroceryItem existingItem = itemOptional.get();
            if (groceryItem.getName() != null && !groceryItem.getName().isEmpty()) {
                existingItem.setName(groceryItem.getName());
            }
            if (groceryItem.getCategory() != null && !groceryItem.getCategory().isEmpty()) {
                existingItem.setCategory(groceryItem.getCategory());
            }
            itemRepository.save(existingItem);
            return "---ITEM ACTUALIZADO PARCIALMENTE---\n" + existingItem.toString();
        } else {
            return "---ITEM NO ENCONTRADO---";
        }
    }

    // Actualización dinámica de un ítem (PATCH)
    public String updateData(String id, GroceryItem groceryItem) {
        Optional<GroceryItem> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            GroceryItem existingItem = itemOptional.get();

            if (groceryItem.getName() != null && !groceryItem.getName().isEmpty()) {
                existingItem.setName(groceryItem.getName());
            }
            if (groceryItem.getQuantity() != 0) {
                existingItem.setQuantity(groceryItem.getQuantity());
            }
            if (groceryItem.getCategory() != null && !groceryItem.getCategory().isEmpty()) {
                existingItem.setCategory(groceryItem.getCategory());
            }

            itemRepository.save(existingItem);
            return "---ITEM ACTUALIZADO (PATCH)---\n" + existingItem.toString();
        } else {
            return "---ITEM NO ENCONTRADO---";
        }
    }
}
