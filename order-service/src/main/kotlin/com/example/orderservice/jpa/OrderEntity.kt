package com.example.orderservice.jpa

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "orders")
class OrderEntity(
    @Column var orderId: String,
    @Column var quantity: Int,
    @Column var unitPrice: Int,
    @Column var totalPrice: Int,
    @Column var productId: String,
    @Column var userId: String,
    @Column var createdDate: LocalDate = LocalDate.now()
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}