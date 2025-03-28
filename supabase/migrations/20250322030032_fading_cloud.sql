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



-- Infrastructure table
CREATE TABLE IF NOT EXISTS infrastructure (
                                              id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                              url_int VARCHAR(255) NOT NULL,
                                              url_uat VARCHAR(255) NOT NULL,
                                              url_oat VARCHAR(255) NOT NULL,
                                              url_prod VARCHAR(255) NOT NULL,
                                              created_at TIMESTAMPTZ DEFAULT now(),
                                              updated_at TIMESTAMPTZ DEFAULT now()
);

-- Data sources table
CREATE TABLE IF NOT EXISTS data_sources (
                                            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                            rabbit_mq BOOLEAN DEFAULT false,
                                            common_db BOOLEAN DEFAULT false,
                                            dedicated_db BOOLEAN DEFAULT false,
                                            s3 BOOLEAN DEFAULT false,
                                            created_at TIMESTAMPTZ DEFAULT now(),
                                            updated_at TIMESTAMPTZ DEFAULT now()
);

-- Api service table
CREATE TABLE IF NOT EXISTS api_service (
                                           id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                           name VARCHAR(255) NOT NULL,
                                           trigramme VARCHAR(3) NOT NULL UNIQUE,
                                           description VARCHAR(255),
                                           infrastructure_id UUID REFERENCES infrastructure(id) ON DELETE SET NULL,
                                           data_sources_id UUID REFERENCES data_sources(id) ON DELETE SET NULL,
                                           database_schema VARCHAR(255),
                                           created_at TIMESTAMPTZ DEFAULT now(),
                                           updated_at TIMESTAMPTZ DEFAULT now()
);

-- Client consumer table
CREATE TABLE IF NOT EXISTS client_consumer (
                                               id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                               service_id UUID REFERENCES api_service(id) ON DELETE CASCADE,
                                               name VARCHAR(255) NOT NULL,
                                               created_at TIMESTAMPTZ DEFAULT now(),
                                               updated_at TIMESTAMPTZ DEFAULT now()
);

-- Consumed by table
CREATE TABLE IF NOT EXISTS consumed_by (
                                           id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                           service_id UUID REFERENCES api_service(id) ON DELETE CASCADE,
                                           name VARCHAR(255) NOT NULL,
                                           created_at TIMESTAMPTZ DEFAULT now(),
                                           updated_at TIMESTAMPTZ DEFAULT now()
);

-- Consumes table
CREATE TABLE IF NOT EXISTS consumes (
                                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                        service_id UUID REFERENCES api_service(id) ON DELETE CASCADE,
                                        name VARCHAR(255) NOT NULL,
                                        created_at TIMESTAMPTZ DEFAULT now(),
                                        updated_at TIMESTAMPTZ DEFAULT now()
);

-- Endpoint table
CREATE TABLE IF NOT EXISTS endpoint (
                                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                        service_id UUID REFERENCES api_service(id) ON DELETE CASCADE,
                                        method VARCHAR(10) NOT NULL,
                                        path VARCHAR(255) NOT NULL,
                                        curl TEXT NOT NULL,
                                        expected_response TEXT NOT NULL,
                                        description TEXT,
                                        created_at TIMESTAMPTZ DEFAULT now(),
                                        updated_at TIMESTAMPTZ DEFAULT now()
);