package com.teo.hospitalapp.parse

import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import com.teo.hospitalapp.data.Hospital
import com.teo.hospitalapp.data.Sector
import com.teo.hospitalapp.data.SubType
import java.io.File
import java.io.Reader


class Parser {

    private val ORG_ID = 0
    private val ORG_CODE = 1
    private val ORG_TYPE = 2
    private val ORG_SUBTYPE = 3
    private val SEC =4
    private val ORG_STATUS =5
    private val ORG_ISPIMS = 6
    private val ORG_NAME =7
    private val ORG_ADD1 = 8
    private val ORG_ADD2 =9
    private val ORG_ADD3 =10
    private val ORG_CITY =11
    private val ORG_COUNTY =12
    private val ORG_POSTCODE =13
    private val ORG_LAT =14
    private val ORG_LONG =15
    private val ORG_ODS =16
    private val ORG_PNAME =17
    private val ORG_PHONE =18
    private val ORG_EMAIL =19
    private val ORG_WEBSITE =20
    private val ORG_FAX =21


     public  fun toDataSet(reader: Reader): List<Hospital> {

         val csvParser = CSVParserBuilder()
             .withSeparator('�')
             .build()
            val csvReader = CSVReaderBuilder(reader)
                .withCSVParser(csvParser)
                .withSkipLines(1)
                .build()

            val hospitals = mutableListOf<Hospital>()
            var record = csvReader.readNext()

            while (record != null) {
                hospitals.add(Hospital(organisationID = record[ORG_ID].toInt(), organisationCode = record[ORG_CODE], organisationType = record[ORG_TYPE], subType = record[ORG_SUBTYPE], sector = record[SEC] ,
                organisationStatus = record[ORG_STATUS], isPimsManaged = record[ORG_ISPIMS].toBoolean(), organisationName = record[ORG_NAME], address1 = record[ORG_ADD1],
                address2 = record[ORG_ADD2], address3 = record[ORG_ADD3], city = record[ORG_CITY], county = record[ORG_COUNTY],
                postcode = record[ORG_POSTCODE], latitude = record[ORG_LAT], longitude = record[ORG_LONG], parentODSCode = record[ORG_ODS], parentName = record[ORG_PNAME], phone = record[ORG_PHONE], email = record[ORG_EMAIL],
                website = record[ORG_WEBSITE], fax = record[ORG_FAX]))

                record = csvReader.readNext()
            }

            return hospitals
        }

    private fun mapToSector(s: String?): Sector {
        if (s != null) {
            if(s.contains("NHS")) { return Sector.NHS }
        }
        return Sector.Independent
    }

    private fun mapToSubType(s: String?): SubType {

        if (s != null) {
            if (s.contains("Mental")) {
                return SubType.MentalHealth
            } else if (s.startsWith("Hospital")) {
                return SubType.Hospital
            }
        }
             return SubType.UNKNOWN

    }

    public fun processLineByLine(csv: File, processor: (Map<String, String>) -> Unit)  {
       // val sc = "\\u00ac"
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
