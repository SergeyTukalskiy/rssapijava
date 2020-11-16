CREATE TABLE "rss_source"
(
    "id" SERIAL PRIMARY KEY,
    "name" TEXT DEFAULT '' NOT NULL,
    "link" TEXT DEFAULT '' NOT NULL
);

CREATE TABLE "rss_item"
(
  "id" BIGSERIAL PRIMARY KEY,
  "source_id" INTEGER REFERENCES "rss_source"("id"),
  "title" TEXT DEFAULT '' NOT NULL,
  "link" TEXT DEFAULT '' NOT NULL,
  "date" TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW() NOT NULL
);
