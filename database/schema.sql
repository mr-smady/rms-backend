CREATE TABLE municipality
(
    id      INT PRIMARY KEY IDENTITY (1, 1),
    name_ar NVARCHAR(100) NOT NULL,
    name_en NVARCHAR(100) NOT NULL,
    CONSTRAINT unique_municipality_name_ar UNIQUE (name_ar),
    CONSTRAINT unique_municipality_name_en UNIQUE (name_en)
);

CREATE TABLE sub_municipality
(
    id              INT PRIMARY KEY IDENTITY (1, 1),
    municipality_id INT NOT NULL,
    name_ar         NVARCHAR(100) NOT NULL,
    name_en         NVARCHAR(100) NOT NULL,
    CONSTRAINT fk_sub_municipality_municipality
        FOREIGN KEY (municipality_id)
            REFERENCES municipality (id),
    CONSTRAINT unique_sub_municipality_name_ar UNIQUE (name_ar),
    CONSTRAINT unique_sub_municipality_name_en UNIQUE (name_en)
);

CREATE TABLE district
(
    id                  INT PRIMARY KEY IDENTITY (1, 1),
    sub_municipality_id INT NOT NULL,
    name_ar             NVARCHAR(100) NOT NULL,
    name_en             NVARCHAR(100) NOT NULL,
    CONSTRAINT fk_district_sub_municipality
        FOREIGN KEY (sub_municipality_id)
            REFERENCES sub_municipality (id),
    CONSTRAINT unique_district_name_ar UNIQUE (name_ar),
    CONSTRAINT unique_district_name_en UNIQUE (name_en)
);

CREATE TABLE avl_provider
(
    id          INT PRIMARY KEY IDENTITY (1, 1),
    name_ar     NVARCHAR(100) NOT NULL,
    name_en     NVARCHAR(100) NOT NULL,
    district_id INT NOT NULL,
    deleted     BIT NOT NULL,
    CONSTRAINT fk_avl_provider_district
        FOREIGN KEY (district_id)
            REFERENCES district (id),
    CONSTRAINT unique_avl_provider_name_ar UNIQUE (name_ar),
    CONSTRAINT unique_avl_provider_name_en UNIQUE (name_en)
);

CREATE TABLE department
(
    id          INT PRIMARY KEY IDENTITY (1, 1),
    name_ar     NVARCHAR(100) NOT NULL,
    name_en     NVARCHAR(100) NOT NULL,
    district_id INT   NOT NULL,
    latitude    FLOAT NOT NULL,
    longitude   FLOAT NOT NULL,
    deleted     BIT   NOT NULL,
    CONSTRAINT fk_department_district
        FOREIGN KEY (district_id)
            REFERENCES district (id),
    CONSTRAINT unique_department_name_ar UNIQUE (name_ar),
    CONSTRAINT unique_department_name_en UNIQUE (name_en)
);

CREATE TABLE trade_license_types
(
    id      INT PRIMARY KEY IDENTITY (1, 1),
    name_ar NVARCHAR(100) NOT NULL,
    name_en NVARCHAR(100) NOT NULL,
    CONSTRAINT unique_trade_license_types_name_ar UNIQUE (name_ar),
    CONSTRAINT unique_trade_license_types_name_en UNIQUE (name_en)
);

CREATE TABLE account
(
    id                     INT PRIMARY KEY IDENTITY (1, 1),
    is_transporter         bit   NOT NULL,
    name_ar                NVARCHAR(100) NOT NULL,
    name_en                NVARCHAR(100) NOT NULL,
    trade_license_types_id INT   NOT NULL,
    account_no             NVARCHAR(100) NOT NULL,
    latitude               FLOAT NOT NULL,
    longitude              FLOAT NOT NULL,
    department_id          INT   NOT NULL,
    accounts_detail_id     INT   NOT NULL,
    is_active              BIT   NOT NULL,
    deleted                BIT   NOT NULL,
    CONSTRAINT fk_account_trade_license_types
        FOREIGN KEY (trade_license_types_id)
            REFERENCES trade_license_types (id),
    CONSTRAINT fk_account_department
        FOREIGN KEY (department_id)
            REFERENCES department (id),
);
--

CREATE TABLE vehicle
(
    id                       int PRIMARY KEY IDENTITY(1,1),
    last_latitude            float NULL,
    last_longitude           float NULL,
    last_speed               float NULL,
    last_position_time       datetime NULL,
    last_time_update         datetime NULL,
    last_course              float NULL,
    plate_number             nvarchar( MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    year_of_made             nvarchar( MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    vehicle_brand            nvarchar( MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    imei                     nvarchar(128) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    vehicle_model            nvarchar( MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    account_id               bigint NULL,
    is_deleted               bit NULL,
    is_active                bit NULL,
    vehicle_description_id   int NULL,
    area_id                  int NULL,
    vehicles_avl_provider_id int NULL,
    project_type_id          int NULL,
    cleaning_project_id      int NULL,
    updated_date             datetime NULL,
    license_number           nvarchar( MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    license_date             datetime NULL,
    vehicle_project_id       int NULL,
    department_id            int NULL
);

CREATE TABLE zone_type
(
    id      INT PRIMARY KEY IDENTITY (1, 1),
    name_ar NVARCHAR(100) NOT NULL,
    name_en NVARCHAR(100) NOT NULL,
    deleted bit NULL
);

CREATE TABLE company
(
    id      INT PRIMARY KEY IDENTITY (1, 1),
    name_ar NVARCHAR(100) NOT NULL,
    name_en NVARCHAR(100) NOT NULL
);


CREATE TABLE alarm
(
    id           INT PRIMARY KEY IDENTITY (1, 1),
    type_id      INT,
    number       INT,
    time         DATETIME,
    zone_type_id INT,
    vehicle_id   INT,
    company_id   INT,
    FOREIGN KEY (zone_type_id) REFERENCES zone_type (id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle (id),
    FOREIGN KEY (company_id) REFERENCES company (id),
);

CREATE TABLE alarm_type
(
    id      INT PRIMARY KEY IDENTITY (1, 1),
    name_ar NVARCHAR(50) NOT NULL,
    name_en NVARCHAR(50) NOT NULL,
);

ALTER TABLE alarm
    ADD FOREIGN KEY (type_id) REFERENCES alarm_type (id);

CREATE TABLE alarm_status
(
    id      INT PRIMARY KEY IDENTITY (1, 1),
    name_ar NVARCHAR(50) NOT NULL,
    name_en NVARCHAR(50) NOT NULL,
);
ALTER TABLE alarm
    ADD status_id INT;
ALTER TABLE alarm
    ADD FOREIGN KEY (status_id) REFERENCES alarm_status (id);

CREATE TABLE vehicle_description
(
    id      INT PRIMARY KEY IDENTITY (1, 1),
    name_ar NVARCHAR(50) NOT NULL,
    name_en NVARCHAR(50) NOT NULL,
);

ALTER TABLE vehicle
    ADD FOREIGN KEY (vehicle_description_id) REFERENCES vehicle_description (id);



CREATE TABLE waste_type
(
    id      INT PRIMARY KEY IDENTITY (1, 1),
    name_ar NVARCHAR(50) NOT NULL,
    name_en NVARCHAR(50) NOT NULL,
);

ALTER TABLE vehicle
    ADD waste_type_id INT;
ALTER TABLE vehicle
    ADD FOREIGN KEY (waste_type_id) REFERENCES waste_type (id);


CREATE TABLE trip
(
    id               INT PRIMARY KEY IDENTITY (1, 1),
    start_date       datetime,
    end_date         datetime,
    distance_covered INT,
    min_speed        INT,
    max_speed        INT,
    avg_speed        INT,
    vehicle_id       INT,
);

ALTER TABLE trip
    ADD FOREIGN KEY (vehicle_id) REFERENCES vehicle (id);


CREATE TABLE waste_producer
(
    id INT PRIMARY KEY IDENTITY (1, 1),
    name_ar NVARCHAR(256) NOT NULL,
    name_en NVARCHAR(256) NOT NULL,
);

CREATE TABLE manifest_status
(
    id INT PRIMARY KEY IDENTITY (1, 1),
    name_ar NVARCHAR(50) NOT NULL,
    name_en NVARCHAR(50) NOT NULL,
);

CREATE TABLE manifest
(
    id                  INT PRIMARY KEY IDENTITY (1, 1),
    [number]            INT,
    waste_producer_id   INT,
    waste_type_id       INT,
    status_id           INT,
    assignment_date     datetime,
);

ALTER TABLE manifest
    ADD FOREIGN KEY (waste_producer_id) REFERENCES waste_producer (id);

ALTER TABLE manifest
    ADD FOREIGN KEY (waste_type_id) REFERENCES waste_type (id);

ALTER TABLE manifest
    ADD FOREIGN KEY (status_id) REFERENCES manifest_status (id);


CREATE TABLE vehicle_movement_status
(
    id INT PRIMARY KEY IDENTITY (1, 1),
    name_ar NVARCHAR(50) NOT NULL,
    name_en NVARCHAR(50) NOT NULL,
);

ALTER TABLE vehicle
    ADD movement_status_id INT;

ALTER TABLE vehicle
    ADD FOREIGN KEY (movement_status_id) REFERENCES vehicle_movement_status (id);


ALTER TABLE manifest
    ADD vehicle_id INT;

ALTER TABLE manifest
    ADD FOREIGN KEY (vehicle_id) REFERENCES vehicle (id);

CREATE TABLE location
(
    id INT PRIMARY KEY IDENTITY (1, 1),
    vehicle_id INT,
    latitude FLOAT,
    longitude FLOAT,
    speed FLOAT,
    ignition BIT,
    receivedServerTime DATETIME
);


ALTER TABLE manifest
    ADD latitude FLOAT;
ALTER TABLE manifest
    ADD longitude FLOAT;

CREATE TABLE zone_type
(
    id      INT PRIMARY KEY IDENTITY (1, 1),
    name_ar NVARCHAR(50) NOT NULL,
    name_en NVARCHAR(50) NOT NULL
);

CREATE TABLE [zone]
(
    id INT PRIMARY KEY IDENTITY (1, 1),
    name_ar NVARCHAR(50) NOT NULL,
    name_en NVARCHAR(50) NOT NULL,
    zone_type_id INT
    )


CREATE TABLE zone_point
(
    id         INT PRIMARY KEY IDENTITY (1, 1),
    latitude   FLOAT,
    longitude  FLOAT,
    zone_id	   INT,
);
CREATE TABLE Test (
	id int IDENTITY(0,1) NOT NULL,
	Name varchar(100) NULL,
	CONSTRAINT Test_PK PRIMARY KEY (id)
);
ALTER TABLE vehicle
    ADD alarms_count INT;

ALTER TABLE zone_type
ADD zones_count INT

UPDATE master.dbo.zone_type
SET zones_count = (
    SELECT COUNT(*)
    FROM master.dbo.zone
    WHERE master.dbo.zone_type.id = master.dbo.zone.zone_type_id
);

CREATE TRIGGER UpdateZoneTypeZonesCount
ON master.dbo.[zone]
AFTER INSERT, UPDATE, DELETE
AS
BEGIN

    UPDATE ZT
    SET ZT.zones_count = (SELECT COUNT(*) FROM master.dbo.[zone] WHERE zone_type_id = ZT.id)
    FROM master.dbo.zone_type AS ZT
    JOIN inserted AS I ON ZT.id = I.zone_type_id
    WHERE I.zone_type_id IS NOT NULL;

    UPDATE ZT
    SET ZT.zones_count = (SELECT COUNT(*) FROM master.dbo.[zone] WHERE zone_type_id = ZT.id)
    FROM master.dbo.zone_type AS ZT
    JOIN deleted AS D ON ZT.id = D.zone_type_id
    WHERE D.zone_type_id IS NOT NULL;
END;
GO

ALTER TABLE master.dbo.avl_last_data ADD update_date datetime NULL;

ALTER TABLE master.dbo.vehicle ADD company_id int NULL;
ALTER TABLE master.dbo.vehicle ADD CONSTRAINT vehicle_FK FOREIGN KEY (company_id) REFERENCES master.dbo.company(id);
ALTER TABLE master.dbo.avl_last_data ADD company_id int NULL;
ALTER TABLE master.dbo.avl_last_data ADD waste_type_id int NULL;
ALTER TABLE master.dbo.alarm ADD vehicle_plate_number varchar(100) NULL;
ALTER TABLE master.dbo.avl_last_data ADD alarms_count int NULL;
ALTER TABLE master.dbo.avl_last_data ADD alarms_count int NULL;



-- CREATE A TRIGGER THAT ADD A NEW ROW IN avl_last_data WHEN INSERT A NEW RECORD IN avl_data
-- CREATE A TRIGGER THAT ADD A NEW ROW IN avl_last_data WHEN INSERT A NEW RECORD IN avl_data
CREATE TRIGGER UpdateAVLLastData
ON master.dbo.avl_data
AFTER INSERT
AS
BEGIN
    MERGE INTO master.dbo.avl_last_data AS target
    USING inserted AS source ON target.plate_number = source.plate_number
    WHEN MATCHED THEN
        UPDATE SET
            target.movement_time = source.movement_time,
            target.priority = source.priority,
            target.longitude = source.longitude,
            target.latitude = source.latitude,
            target.altitude = source.altitude,
            target.angle = source.angle,
            target.speed = source.speed,
            target.movement = source.movement,
            target.digital_input1 = source.digital_input1,
            target.analog_input1 = source.analog_input1,
            target.ignition = source.ignition,
            target.distance = source.distance,
            target.total_distance = source.total_distance,
            target.green_driving_type = source.green_driving_type,
            target.event_id = source.event_id,
            target.tag = source.tag,
            target.last_tag = source.last_tag,
            target.waste_collection_time = source.waste_collection_time,
            target.waste_latitude = source.waste_latitude,
            target.waste_longitude = source.waste_longitude,
            target.gross_weight = source.gross_weight,
            target.ed0 = source.ed0,
            target.ed1 = source.ed1,
            target.input1 = source.input1,
            target.input2 = source.input2,
            target.input3 = source.input3,
            target.input4 = source.input4,
            target.input5 = source.input5,
            target.plate_number = source.plate_number,
            target.update_date = GETUTCDATE(),
            target.company_id = (
                SELECT TOP 1 company_id
                FROM master.dbo.vehicle
                WHERE plate_number = source.plate_number
            ),
            target.waste_type_id = (
            SELECT TOP 1 waste_type_id
            FROM master.dbo.vehicle
            WHERE plate_number = source.plate_number
            )
    WHEN NOT MATCHED THEN
        INSERT (imei, movement_time, priority, longitude, latitude, altitude, angle, speed,
                movement, digital_input1, analog_input1, ignition, distance, total_distance,
                green_driving_type, event_id, tag, last_tag, waste_collection_time,
                waste_latitude, waste_longitude, gross_weight, ed0, ed1, input1, input2,
                input3, input4, input5, plate_number, update_date, company_id , waste_type_id)
        VALUES (
                source.imei, source.movement_time, source.priority, source.longitude,
                source.latitude, source.altitude, source.angle, source.speed,
                source.movement, source.digital_input1, source.analog_input1,
                source.ignition, source.distance, source.total_distance,
                source.green_driving_type, source.event_id, source.tag,
                source.last_tag, source.waste_collection_time, source.waste_latitude,
                source.waste_longitude, source.gross_weight, source.ed0, source.ed1,
                source.input1, source.input2, source.input3, source.input4,
                source.input5, source.plate_number, GETUTCDATE(),
                (
                    SELECT TOP 1 company_id
                    FROM master.dbo.vehicle
                    WHERE plate_number = source.plate_number
                ),
                (
                    SELECT TOP 1 waste_type_id
                    FROM master.dbo.vehicle
                    WHERE plate_number = source.plate_number
                 )
        );
END;



-- BEGIN
-- 	DECLARE @i INT = 0;
-- 	DECLARE @zone_type_id INT;
-- 	DECLARE @vehiclet_id INT;
-- 	DECLARE @company_id INT;
-- 	DECLARE @alarm_status_id INT;
-- 	DECLARE @time_value DATETIME2(0);
-- 	DECLARE @RtnValue DECIMAL(18,4);
-- 	DECLARE @RV INT;
--     WHILE @i < 10
-- BEGIN
--         SET @time_value = dbo.randomDatetime('2023-01-01 08:22:13','2023-08-08 17:56:31');
--         SET @zone_type_id = (SELECT TOP 1 t.id FROM zone_type t ORDER BY NEWID());
--         SET @vehiclet_id = (SELECT TOP 1 t.id FROM vehicle t ORDER BY NEWID());
-- 	    SET @company_id = (SELECT TOP 1 t.id FROM company t ORDER BY NEWID());
-- 	    SET @alarm_status_id = (SELECT TOP 1 t.id FROM alarm_status t ORDER BY NEWID());
--         SET @RtnValue= (SELECT Value FROM vw_getRANDValue);
--         SET @RV =  FLOOR(@RtnValue * (1000- 100 + 1)) + 100;
--
-- INSERT INTO alarm (name,[number],[time],zone_type_id,vehicle_id,company_id, status_id)
-- VALUES ('',@RV,@time_value,@zone_type_id,@vehiclet_id,@company_id,@alarm_status_id);
--
-- SET @i = @i + 1;
-- END
-- END


-- DBCC CHECKIDENT ('school', RESEED, 0);



-- BEGIN
-- 	DECLARE @i INT = 0
-- 	DECLARE @min_latitude FLOAT = (SELECT MIN(l.latitude) FROM location l);
-- 	DECLARE @max_latitude FLOAT = (SELECT MAX(l.latitude) FROM location l);
-- 	DECLARE @min_longitude FLOAT = (SELECT MIN(l.longitude) FROM location l);
-- 	DECLARE @max_longitude FLOAT = (SELECT MAX(l.longitude) FROM location l);
-- 	DECLARE @latitude FLOAT;
-- 	DECLARE @longitude FLOAT;
-- 	WHILE @i <= 100000
-- BEGIN
-- 			SET @latitude = (SELECT RAND()*(@max_latitude-@min_latitude)+@min_latitude);
-- 			SET @latitude = (SELECT RAND()*(@max_longitude-@min_longitude)+@min_longitude);
-- UPDATE manifest SET latitude = @latitude, longitude = @longitude;
-- SET @i = @i + 1;
-- END
-- END