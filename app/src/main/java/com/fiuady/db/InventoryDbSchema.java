package com.fiuady.db;

/**
 * Created by Saul on 17/03/2017.
 */

public final class InventoryDbSchema {

    public static final class CategoriesTable {
        public static final String NAME = "categories";

        public static final class Columns{
            public static final String ID ="id";
            public static final String DESCRIPTION = "description"
        }
    }

    public static final class ProductsTable{
        public static final String NAME = "products";

        public static final class Columns {
            public static final String ID = "id";
            public static final String CATEGORY_ID = "category_id";
            public static final String DESCRIPTION ="description";
            public static final String PRICE ="price";
            public static final String QUANTITY = "quantity";

        }
    }
}
