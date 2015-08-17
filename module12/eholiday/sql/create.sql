CREATE TABLE ACCOMMODATION
(
  ID              CHAR(6 BYTE)         NOT NULL,
  NAME            NVARCHAR2(256)       NOT NULL,
  IN_DB_SINCE     DATE                 NOT NULL,
  COPIED          DATE                 NOT NULL,
  CATEGORY        NUMBER(4, 0)         NOT NULL,
  STREET_ADDRESS  NVARCHAR2(64)        NOT NULL,
  POSTAL_CODE     CHAR(6 BYTE)         NOT NULL,
  LOCALITY        NVARCHAR2(64)        NOT NULL,
  REGION          NVARCHAR2(64)        NOT NULL,
  COUNTRY         NVARCHAR2(64)        NOT NULL,
  LONGITUDE       NUMBER(16, 13)       NOT NULL,
  LATITUDE        NUMBER(16, 13)       NOT NULL,
  STARS           NUMBER(1, 0)         NOT NULL,
  PRICE_FROM      NUMBER(8, 2),
  CURRENCY        CHAR(3 BYTE),
  PRICE_UNIT      NUMBER(1, 0),
  WEBSITE1        VARCHAR2(256 BYTE),
  WEBSITE2        VARCHAR2(256 BYTE),
  SKYPE           VARCHAR2(64 BYTE),
  GG              VARCHAR2(16 BYTE),
  FACEBOOK        VARCHAR2(256 BYTE),
  PHONE1          VARCHAR2(32 BYTE),
  PHONE2          VARCHAR2(32 BYTE),
  MOB_PHONE1      VARCHAR2(32 BYTE),
  MOB_PHONE2      VARCHAR2(32 BYTE),
  INFOLINE        VARCHAR2(32 BYTE),
  FAX             VARCHAR2(32 BYTE),
  PHOTO           BLOB,
  PHOTO_URL       VARCHAR2(256 BYTE),

  CONSTRAINT ACCOMMODATION_PK PRIMARY KEY (ID) ENABLE
);