package com.lucasnvs.cadmo.data

import com.lucasnvs.cadmo.data.source.local.LocalProduct
import com.lucasnvs.cadmo.data.source.network.NetworkProduct


// External to local
fun Product.toLocal() = LocalProduct(
    id = id,
    name = name,
    price = price,
    imgSrc = imgSrc,
    isOpenBox = isOpenBox
)

fun List<Product>.toLocal() = map(Product::toLocal)

// Local to External
fun LocalProduct.toExternal() = Product(
    id = id,
    name = name,
    price = price,
    imgSrc = imgSrc,
    isOpenBox = isOpenBox
)

// Nota: O JvmName é usado pra prover o nome único pra cada extensão da função com o mesmo nome.
// Sem isso, o "type erasure" vai causar erros de compilação porque estes métodos vão ter a mesma assinatura na JVM.
@JvmName("localToExternal")
fun List<LocalProduct>.toExternal() = map(LocalProduct::toExternal)

// Network to Local
fun NetworkProduct.toLocal() = LocalProduct(
    id = id,
    name = name,
    price = price,
    imgSrc = imgSrc,
    isOpenBox = isOpenBox
)

@JvmName("networkToLocal")
fun List<NetworkProduct>.toLocal() = map(NetworkProduct::toLocal)

// Local to Network
fun LocalProduct.toNetwork() = NetworkProduct(
    id = id,
    name = name,
    price = price,
    imgSrc = imgSrc,
    isOpenBox = isOpenBox
)

fun List<LocalProduct>.toNetwork() = map(LocalProduct::toNetwork)

// External to Network
fun Product.toNetwork() = toLocal().toNetwork()

@JvmName("externalToNetwork")
fun List<Product>.toNetwork() = map(Product::toNetwork)

// Network to External
fun NetworkProduct.toExternal() = toLocal().toExternal()

@JvmName("networkToExternal")
fun List<NetworkProduct>.toExternal() = map(NetworkProduct::toExternal)