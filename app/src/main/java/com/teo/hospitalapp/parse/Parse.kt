package com.teo.hospitalapp.parse

import com.opencsv.CSVReaderBuilder
import com.teo.hospitalapp.data.Hospital
import java.io.File
import java.io.Reader

class Parser {


        fun toDataSet(reader: Reader): List<Hospital> {

            val csvReader = CSVReaderBuilder(reader)
                .withSkipLines(1)
                .build()

            val hospitals = mutableListOf<Hospital>()
            var record = csvReader.readNext()

            while (record != null) {
               // hospitals.addAll()add(Hospital(....)))
                record = csvReader.readNext()
            }

            return hospitals
        }
    fun processLineByLine(csv: File, processor: (Map<String, String>) -> Unit)  {
        val sc = "\\u00ac"
        val header = csv.useLines { it.firstOrNull()?.split("¬") }
            ?: throw Exception("This file does not contain a valid header")

        csv.useLines { linesSequence ->
            linesSequence
                .drop(1)
                .map { it.split("¬") }
                .map { header.zip(it).toMap() }
                .forEach(processor)
        }
    }

}
