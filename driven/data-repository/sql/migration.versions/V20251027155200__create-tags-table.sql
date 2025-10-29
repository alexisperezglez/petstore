CREATE TABLE IF NOT EXISTS tags (
    id    UUID PRIMARY KEY,
    value VARCHAR(30) NOT NULL UNIQUE
);

COMMENT ON TABLE tags IS 'Table to store pet tags';
COMMENT ON COLUMN tags.id IS 'Primary key of the tag (record UUID)';
COMMENT ON COLUMN tags.value IS 'Required unique value of the tag (max length 30)';

CREATE INDEX IF NOT EXISTS idx_tags_value ON tags (value);
