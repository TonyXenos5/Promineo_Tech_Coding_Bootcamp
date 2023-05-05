DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS project_category;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS project;

CREATE TABLE project (
	project_id INTEGER AUTO_INCREMENT NOT NULL,
	project_name VARCHAR(128) NOT NULL,
	estimated_hours DECIMAL (7,2),
	actual_hours DECIMAL (7,2),
	difficulty INTEGER,
	notes TEXT,
	PRIMARY KEY (project_id)
);

CREATE TABLE category (
	category_id INTEGER AUTO_INCREMENT NOT NULL,
	category_name VARCHAR(128) NOT NULL,
	PRIMARY KEY (category_id)
);

CREATE TABLE project_category (
	project_id INTEGER AUTO_INCREMENT NOT NULL,
	category_id INTEGER NOT NULL,
	FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE,
	FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE,
	UNIQUE KEY (project_id, category_id)
);

CREATE TABLE material (
	material_id INTEGER AUTO_INCREMENT NOT NULL,
	project_id INTEGER NOT NULL,
	material_name VARCHAR(128) NOT NULL,
	num_required INTEGER,
	cost DECIMAL(7,2),
	PRIMARY KEY (material_id),
	FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
);

CREATE TABLE step (
	step_id INTEGER AUTO_INCREMENT NOT NULL,
	project_id INTEGER NOT NULL,
	step_text TEXT NOT NULL,
	step_order INTEGER NOT NULL,
	PRIMARY KEY (step_id),
	FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
);

-- Adding data

INSERT INTO project (project_name, estimated_hours, actual_hours, difficulty, notes) VALUES ('Hang a door', 4, 3, 3, 'Use the door hangers from Home Depot');
INSERT INTO material (project_id, material_name, num_required, cost) VALUES (1, 'Door hangers', 3, 10.25);
INSERT INTO material (project_id, material_name, num_required, cost) VALUES (1, 'Screws', 20, 14.25);
INSERT INTO step (project_id, step_text, step_order) VALUES (1, 'Align hangers on opening side of the door', 1);
INSERT INTO step (project_id, step_text, step_order) VALUES (1, 'Screw hangers into frame', 2);
INSERT INTO category (category_id, category_name) VALUES (1, 'Doors and Windows');
INSERT INTO category (category_id, category_name) VALUES (2, 'Repairs');
INSERT INTO category (category_id, category_name) VALUES (3, 'Gardening');
INSERT INTO project_category (project_id, category_id) VALUES (1, 1);
INSERT INTO project_category (project_id, category_id) VALUES (1, 2);

-- Coding Assignment Testing Data

INSERT INTO project (project_name, estimated_hours, actual_hours, difficulty, notes) VALUES ('Java Code', 4, 3, 3, 'Type code into Eclipse');
INSERT INTO material (project_id, material_name, num_required, cost) VALUES (2, 'Computer', 1, 499.99);
INSERT INTO material (project_id, material_name, num_required, cost) VALUES (2, 'Internet', null, 66.16);
INSERT INTO step (project_id, step_text, step_order) VALUES (2, 'Open up IDE window', 1);
INSERT INTO step (project_id, step_text, step_order) VALUES (2, 'Type in Java syntax', 2);
INSERT INTO step (project_id, step_text, step_order) VALUES (2, 'Run the code through the IDE', 3);
INSERT INTO category (category_id, category_name) VALUES (4, 'Technology');
INSERT INTO category (category_id, category_name) VALUES (5, 'Skills');
INSERT INTO project_category (project_id, category_id) VALUES (2, 4);
INSERT INTO project_category (project_id, category_id) VALUES (2, 5);