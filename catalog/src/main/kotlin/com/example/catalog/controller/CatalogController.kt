package com.example.catalog.controller

import com.example.catalog.persistence.CatalogDao
import com.example.catalog.persistence.ProductEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/catalog-service")
@RestController
class CatalogController(
    private val catalogDao: CatalogDao
) {

    @GetMapping("/catalogs")
    fun getCatalogs(): List<ProductEntity> {

        println("getCatalogs()")

        return catalogDao.findAll()
    }
}