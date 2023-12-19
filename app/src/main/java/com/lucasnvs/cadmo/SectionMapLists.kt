package com.lucasnvs.cadmo

import com.lucasnvs.cadmo.ui.components.DataProduct

val sectionMoreSearched = listOf(
    DataProduct("Placa de Vídeo RTX 4060 EAGLE OC Gigabyte NVIDIA GeForce, 8GB GDUAL novo novo novo novo", 1954.99),
    DataProduct("Placa de Vídeo RTX 4060 EAGLE OC Gigabyte NVIDIA GeForce, 8GB GDUAL novo novo novo novo", 1954.99),
    DataProduct("Placa de Vídeo RTX 4060 EAGLE OC Gigabyte NVIDIA GeForce, 8GB GDUAL novo novo novo novo", 1954.99),
    DataProduct("Placa de Vídeo RTX 4060 EAGLE OC Gigabyte NVIDIA GeForce, 8GB GDUAL novo novo novo novo", 1954.99),
)

val sectionRating = listOf(
    DataProduct("Placa", 1954.99),
    DataProduct("Placa de Vídeo RTX 4060 EAGLE OC Gigabyte NVIDIA GeForce, 8GB GDUAL", 1954.99),
    DataProduct("Placa de Vídeo RTX 4060 EAGLE OC Gigabyte NVIDIA GeForce, 8GB GDUAL novo novo novo novo", 1954.99),
    DataProduct("Placa de Vídeo RTX 4060 EAGLE OC Gigabyte NVIDIA GeForce, 8GB GDUAL novo novo novo novo", 1954.99),
)

// Seção
val sectionEntryLevel = listOf(
    DataProduct("Placa de Vídeo GTX 1650 Eagle OC Gigabyte NVIDIA GeForce, 4GB GDDR6", 1399.99),
    DataProduct("Placa de Vídeo GT 1030 2GB DDR5 ZOTAC Gaming GeForce", 699.99),
    DataProduct("Placa de Vídeo GT 1030 2GB DDR5 Zotac GeForce", 699.99),
)

// Seção
val sectionMidRange = listOf(
    DataProduct("Placa de Vídeo RTX 3060 Eagle OC Gigabyte NVIDIA GeForce, 12GB GDDR6", 3999.99),
    DataProduct("Placa de Vídeo RTX 3060 Ti Eagle OC Gigabyte NVIDIA GeForce, 8GB GDDR6", 4999.99),
    DataProduct("Placa de Vídeo RX 6600 XT Gigabyte Gaming OC AMD Radeon, 8GB GDDR6", 4499.99),
)

// Seção
val sectionHighEnd = listOf(
    DataProduct("Placa de Vídeo RTX 3080 Ti Founders Edition NVIDIA GeForce, 12GB GDDR6X", 10999.99),
    DataProduct("Placa de Vídeo RTX 3080 Ti Eagle OC Gigabyte NVIDIA GeForce, 12GB GDDR6X", 11999.99),
    DataProduct("Placa de Vídeo RX 6900 XT Gaming OC AMD Radeon, 16GB GDDR6", 12499.99),
)

val sections = listOf(
    sectionMoreSearched,
    sectionRating,
    sectionEntryLevel,
    sectionMidRange,
    sectionHighEnd
)

val sectionNames = listOf(
    "Mais Procurados",
    "Bem classificados",
    "Placas de vídeo de entrada",
    "Placas de vídeo intermediárias",
    "Placas de vídeo de alta performance"
)


val map = sections.zip(sectionNames).toMap()