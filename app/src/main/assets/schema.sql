CREATE TABLE [categories](
    [id] INTEGER PRIMARY KEY,
    [description] TEXT NOT NULL);

CREATE TABLE [products](
    [id] INTEGER PRIMARY KEY,
    [category_id] INTEGER REFERENCES categories([id]),
    [description] TEXT NOT NULL,
    [price] INTEGER NOT NULL,
    [qty] INTEGER NOT NULL);

CREATE TABLE [assemblies](
    [id] INTEGER PRIMARY KEY,
    [description] TEXT NOT NULL);

CREATE TABLE [assembly_products](
    [id] INTEGER NOT NULL REFERENCES [assemblies]([id]),
    [product_id] INTEGER REFERENCES [products]([id]),
    [qty] INTEGER NOT NULL,
    UNIQUE ([id], [product_id]));

CREATE TABLE [orders](
    [id] INTEGER PRIMARY KEY,
    [done] INTEGER NOT NULL);

CREATE TABLE [orders_assemblies](
    [id] INTEGER NOT NULL REFERENCES [orders]([id]),
    [assembly_id] INTEGER NOT NULL REFERENCES [assemblies]([id]),
    [qty] INTEGER NOT NULL,
    UNIQUE([id], [assembly_id]));