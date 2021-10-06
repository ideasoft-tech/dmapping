package uy.ideasoft.o3tng.test

import uy.ideasoft.o3tng.dataset.schema.RecordSchema.{DatasetsSchemas, RecordDatasetColumn, RecordDatasetSchema}


object TestDatasetSpc {

  val datasets: DatasetsSchemas = DatasetsSchemas(
    recordDatasets = Seq(
      RecordDatasetSchema(
        name = "prices",
        cols = Seq(
          RecordDatasetColumn(
            name = "prices_id",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "scene_id",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "simulation_time",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "value",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "product_code",
            label = "",
            valueType = "long",
            order = 0
          ),
        ),
        key = Seq("prices_id")
      ),
      RecordDatasetSchema(
        name = "vloumes",
        cols = Seq(
          RecordDatasetColumn(
            name = "volumes_id",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "scene_id",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "simulation_time",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "value",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "product_code",
            label = "",
            valueType = "long",
            order = 0
          ),
        ),
        key = Seq("volume_id")
      ),
      RecordDatasetSchema(
        name = "incomes",
        cols = Seq(
          RecordDatasetColumn(
            name = "incomes_id",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "scene_id",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "simulation_time",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "value",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "product_code",
            label = "",
            valueType = "long",
            order = 0
          ),
        ),
        key = Seq("incomes_id")
      ),
      RecordDatasetSchema(
        name = "investments",
        cols = Seq(
          RecordDatasetColumn(
            name = "investments_id",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "scene_id",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "simulation_time",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "value",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "product_code",
            label = "",
            valueType = "long",
            order = 0
          ),
        ),
        key = Seq("incomes_id")
      ),
      RecordDatasetSchema(
        name = "expenses",
        cols = Seq(
          RecordDatasetColumn(
            name = "expenses_id",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "scene_id",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "simulation_time",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "value",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "product_code",
            label = "",
            valueType = "long",
            order = 0
          ),
        ),
        key = Seq("expenses_id")
      ),
      RecordDatasetSchema(
        name = "balances",
        cols = Seq(
          RecordDatasetColumn(
            name = "balances_id",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "scene_id",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "simulation_time",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "value",
            label = "",
            valueType = "long",
            order = 0
          ),
          RecordDatasetColumn(
            name = "product_code",
            label = "",
            valueType = "long",
            order = 0
          ),
        ),
        key = Seq("balances_id")
      ),

      RecordDatasetSchema(
        name = "products",
        cols = Seq(
          RecordDatasetColumn(
            name = "code",
            label = "",
            valueType = "string",
            order = 0
          ),
          RecordDatasetColumn(
            name = "name",
            label = "",
            valueType = "string",
            order = 0
          ),
        ),
        key = Seq("code")
      ),

      RecordDatasetSchema(
        name = "simScene",
        cols = Seq(
          RecordDatasetColumn(
            name = "scene_id",
            label = "",
            valueType = "long",
            order = 0
          ),
        ),
        key = Seq("balances_id")
      ),



    )
  )

}
