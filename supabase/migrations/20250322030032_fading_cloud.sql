-- Insert root folders
INSERT INTO folders (name, description, created_at, updated_at) 
VALUES ('Normes Dev', 'Dossier contenant les normes de développement', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO folders (name, description, created_at, updated_at) 
VALUES ('Documentation Technique', 'Documentation technique des projets', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert subfolders
INSERT INTO folders (name, description, parent_id, created_at, updated_at) 
VALUES ('Frontend', 'Normes de développement frontend', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO folders (name, description, parent_id, created_at, updated_at) 
VALUES ('Backend', 'Normes de développement backend', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert documents
INSERT INTO documents (name, description, version, file_type, file_size, file_path, folder_id, created_at, updated_at) 
VALUES ('Guide Style Frontend', 'Guide de style pour le développement frontend', '1.0', 'PDF', 1024, '/files/guide-style-v1.pdf', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO documents (name, description, version, file_type, file_size, file_path, folder_id, created_at, updated_at) 
VALUES ('Convention Nommage', 'Conventions de nommage pour le code', '2.0', 'PDF', 2048, '/files/naming-convention-v2.pdf', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert document tags
INSERT INTO document_tags (document_id, tag) VALUES (1, 'frontend');
INSERT INTO document_tags (document_id, tag) VALUES (1, 'style-guide');
INSERT INTO document_tags (document_id, tag) VALUES (2, 'backend');
INSERT INTO document_tags (document_id, tag) VALUES (2, 'convention');

-- Insert document metadata
INSERT INTO document_metadata (document_id, metadata_key, metadata_value) VALUES (1, 'author', 'Équipe Frontend');
INSERT INTO document_metadata (document_id, metadata_key, metadata_value) VALUES (1, 'department', 'Développement');
INSERT INTO document_metadata (document_id, metadata_key, metadata_value) VALUES (2, 'author', 'Équipe Backend');
INSERT INTO document_metadata (document_id, metadata_key, metadata_value) VALUES (2, 'department', 'Développement');