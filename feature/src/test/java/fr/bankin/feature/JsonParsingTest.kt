package fr.bankin.feature

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import fr.bankin.feature.data.model.ResourceList
import org.junit.Test

class JsonParsingTest {

    val json = """{
        "resources": [
            {
                "id": 325,
                "resource_uri": "/v2/categories/325",
                "resource_type": "category",
                "name": "Dentiste",
                "parent": {
                    "id": 163,
                    "resource_uri": "/v2/categories/163",
                    "resource_type": "category"
                },
                "custom": false,
                "other": false,
                "is_deleted": false
            },
              {
            "id": 163,
            "resource_uri": "/v2/categories/163",
            "resource_type": "category",
            "name": "Santé",
            "parent": null,
            "custom": false,
            "other": false,
            "is_deleted": false
        }
        ],
        "pagination": {
            "previous_uri": null,
            "next_uri": null
        }
    }"""


    @Test
    fun `test json parsing`() {

        // 1. Créez une instance de Moshi
        val moshi = Moshi.Builder().build()

        // 2. Créez une instance de la classe JsonAdapter
        val jsonAdapter: JsonAdapter<ResourceList> = moshi.adapter(ResourceList::class.java)

        // 3. Utilisez la méthode fromJson() pour décoder le JSON en String
        val resourceList = jsonAdapter.fromJson(json)

        assert(resourceList != null)
    }

    @Test
    fun `test json parsingData are ok `() {

        // 1. Créez une instance de Moshi
        val moshi = Moshi.Builder().build()

        // 2. Créez une instance de la classe JsonAdapter
        val jsonAdapter: JsonAdapter<ResourceList> = moshi.adapter(ResourceList::class.java)

        // 3. Utilisez la méthode fromJson() pour décoder le JSON en String
        val resourceList = jsonAdapter.fromJson(json)

        assert(resourceList != null)

        val loCategory = resourceList?.resources?.find {
            it.id == 163
        }

        assert(loCategory != null)
        assert(loCategory?.name == "Santé")


    }
}