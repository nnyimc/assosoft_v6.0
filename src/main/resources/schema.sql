;             
CREATE USER IF NOT EXISTS "SA" SALT 'a59833c10f93d87b' HASH 'aeadf5c28276aed2448a903baef823da43c7ec26a1591879d6f1dc25aef0bf18' ADMIN;         
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_9A425097_BB7A_4FD4_9326_9BA7617FCF44" START WITH 9 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_7ED5BF35_CF39_4F74_B081_DCB663EA7267" START WITH 3 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_CCCB7AED_9E99_48DE_B83A_E57F41493E1C" START WITH 4 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_4561E9AE_8B6B_4638_B083_0701020E1586" START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_132EBCC7_25A7_4832_8E5A_4DD927F17542" START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_572A7425_2E52_4672_B768_D2556F1F0A6E" START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_F282C155_AF28_450F_BEC9_16AE4F8C768B" START WITH 5 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_C233A480_FA17_45BD_8BB6_93D0CE27F96D" START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_0A26C769_4FE9_4C26_BDD6_7BE4F1BD34BD" START WITH 10 BELONGS_TO_TABLE;               
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_13793305_AC0E_4474_9FFB_A136B57A0FB4" START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_58A7F34F_0A79_4678_8896_B906A748EC6A" START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_40708180_A747_4C3D_9D52_B035F5B6D674" START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_A553062F_E437_4314_95D2_FF18CF6CBB74" START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_D38394ED_F097_43F9_90E2_24126036CDF7" START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_41728D56_5983_450B_ACC8_4CF812E9963B" START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_07B792CF_5698_4B05_9EA6_3AD9102C0209" START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_FD833C9C_DD8F_4F99_AD19_74AD91720587" START WITH 2 BELONGS_TO_TABLE;
CREATE MEMORY TABLE "PUBLIC"."ADHESION"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_40708180_A747_4C3D_9D52_B035F5B6D674" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_40708180_A747_4C3D_9D52_B035F5B6D674",
    "ASSOCIATION_ID" BIGINT NOT NULL,
    "PERSONNE_ID" BIGINT NOT NULL
);             
ALTER TABLE "PUBLIC"."ADHESION" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_E" PRIMARY KEY("ID");     
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.ADHESION;
CREATE MEMORY TABLE "PUBLIC"."ADRESSE"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_0A26C769_4FE9_4C26_BDD6_7BE4F1BD34BD" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_0A26C769_4FE9_4C26_BDD6_7BE4F1BD34BD",
    "CODE_POSTAL" VARCHAR(20) NOT NULL,
    "COMPLEMENT" VARCHAR(100),
    "RUE" VARCHAR(250) NOT NULL,
    "VILLE" VARCHAR(250) NOT NULL
);          
ALTER TABLE "PUBLIC"."ADRESSE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_E7" PRIMARY KEY("ID");     
-- 9 +/- SELECT COUNT(*) FROM PUBLIC.ADRESSE; 
INSERT INTO "PUBLIC"."ADRESSE" VALUES
(1, '22100', NULL, '58 Rue de l''Apport', 'Dinan'),
(2, '22100', NULL, '9 Rue de Lehon', 'Dinan'),
(3, '22100', NULL, '14 Rue de La Poissonnerie', 'Dinan'),
(4, '25300', 'Centre Commercial Hyper U', STRINGDECODE('2 Rue de Besan\u00e7on'), 'Doubs'),
(5, '25300', NULL, '3 Rue du Bois de L''Oie', STRINGDECODE('Ar\u00e7on')),
(6, '25300', NULL, '23 Grande Rue', 'Doubs'),
(7, '08200', NULL, '7 Rue Thiers', 'Sedan'),
(8, '08200', NULL, STRINGDECODE('4 Promenoir des Pr\u00eatres'), 'Sedan'),
(9, '08200', NULL, '2 Rue Renan', 'Doubs');          
CREATE MEMORY TABLE "PUBLIC"."ASSOCIATION"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_07B792CF_5698_4B05_9EA6_3AD9102C0209" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_07B792CF_5698_4B05_9EA6_3AD9102C0209",
    "MAIL" VARCHAR(100) NOT NULL,
    "NOM" VARCHAR(100) NOT NULL,
    "NUMRNA" VARCHAR(40) NOT NULL,
    "TELEPHONE" VARCHAR(20),
    "URL" VARCHAR(100),
    "ADRESSE_ID" BIGINT NOT NULL,
    "CATEGORIE_ID" BIGINT NOT NULL,
    "STATUT_ID" BIGINT NOT NULL
);           
ALTER TABLE "PUBLIC"."ASSOCIATION" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_7" PRIMARY KEY("ID");  
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.ASSOCIATION;             
CREATE MEMORY TABLE "PUBLIC"."CATEGORIE"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_CCCB7AED_9E99_48DE_B83A_E57F41493E1C" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_CCCB7AED_9E99_48DE_B83A_E57F41493E1C",
    "NOM" VARCHAR(80) NOT NULL
);      
ALTER TABLE "PUBLIC"."CATEGORIE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_3" PRIMARY KEY("ID");    
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.CATEGORIE;               
INSERT INTO "PUBLIC"."CATEGORIE" VALUES
(1, 'Humanitaire'),
(2, 'Sport'),
(3, STRINGDECODE('Charit\u00e9'));               
CREATE MEMORY TABLE "PUBLIC"."CONTACT_PROFESSIONNEL"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_41728D56_5983_450B_ACC8_4CF812E9963B" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_41728D56_5983_450B_ACC8_4CF812E9963B",
    "ASSOCIATION_ID" BIGINT NOT NULL,
    "CONTACT_ID" BIGINT NOT NULL
); 
ALTER TABLE "PUBLIC"."CONTACT_PROFESSIONNEL" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_C" PRIMARY KEY("ID");        
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.CONTACT_PROFESSIONNEL;   
CREATE MEMORY TABLE "PUBLIC"."DON"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_4561E9AE_8B6B_4638_B083_0701020E1586" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_4561E9AE_8B6B_4638_B083_0701020E1586",
    "DATE" DATE NOT NULL,
    "DESCRIPTION" VARCHAR(100),
    "ASSOCIATION_ID" BIGINT NOT NULL,
    "PERSONNE_ID" BIGINT NOT NULL,
    "STATUT_ID" BIGINT NOT NULL,
    "TYPE_DON_ID" BIGINT NOT NULL
);
ALTER TABLE "PUBLIC"."DON" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_1" PRIMARY KEY("ID");          
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.DON;     
CREATE MEMORY TABLE "PUBLIC"."LIEN_RESEAU_SOCIAL"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_C233A480_FA17_45BD_8BB6_93D0CE27F96D" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_C233A480_FA17_45BD_8BB6_93D0CE27F96D",
    "URL" VARCHAR(250) NOT NULL,
    "ASSOCIATION_ID" BIGINT NOT NULL,
    "RESEAU_SOCIAL_ID" BIGINT NOT NULL
);            
ALTER TABLE "PUBLIC"."LIEN_RESEAU_SOCIAL" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" PRIMARY KEY("ID");           
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.LIEN_RESEAU_SOCIAL;      
CREATE MEMORY TABLE "PUBLIC"."MEDIA"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_132EBCC7_25A7_4832_8E5A_4DD927F17542" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_132EBCC7_25A7_4832_8E5A_4DD927F17542",
    "PATH" VARCHAR(100) NOT NULL,
    "TITRE" VARCHAR(100) NOT NULL,
    "URL" VARCHAR(100) NOT NULL,
    "ASSOCIATION_ID" BIGINT,
    "PERSONNE_ID" BIGINT
);         
ALTER TABLE "PUBLIC"."MEDIA" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_45" PRIMARY KEY("ID");       
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.MEDIA;   
CREATE MEMORY TABLE "PUBLIC"."OFFRE"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_572A7425_2E52_4672_B768_D2556F1F0A6E" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_572A7425_2E52_4672_B768_D2556F1F0A6E",
    "DATE_DEBUT" DATE,
    "DATE_FIN" DATE,
    "ASSOCIATION_ID" BIGINT,
    "PROPOSITION_ID" BIGINT
); 
ALTER TABLE "PUBLIC"."OFFRE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_47" PRIMARY KEY("ID");       
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.OFFRE;   
CREATE MEMORY TABLE "PUBLIC"."PERSONNE"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_A553062F_E437_4314_95D2_FF18CF6CBB74" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_A553062F_E437_4314_95D2_FF18CF6CBB74",
    "EMAIL" VARCHAR(200) NOT NULL,
    "LOGIN" VARCHAR(100) NOT NULL,
    "MOT_DE_PASSE" VARCHAR(255) NOT NULL,
    "NOM" VARCHAR(200) NOT NULL,
    "PRENOM" VARCHAR(100) NOT NULL,
    "ADRESSE_ID" BIGINT NOT NULL,
    "ROLE_ID" BIGINT NOT NULL,
    "STATUT_ID" BIGINT NOT NULL
);         
ALTER TABLE "PUBLIC"."PERSONNE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_49" PRIMARY KEY("ID");    
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.PERSONNE;
CREATE MEMORY TABLE "PUBLIC"."PRESTATAIRE"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_D38394ED_F097_43F9_90E2_24126036CDF7" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_D38394ED_F097_43F9_90E2_24126036CDF7",
    "EMAIL" VARCHAR(100) NOT NULL,
    "NOM" VARCHAR(100) NOT NULL,
    "ADRESSE_ID" BIGINT,
    "STATUT_ID" BIGINT NOT NULL
);   
ALTER TABLE "PUBLIC"."PRESTATAIRE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_3A" PRIMARY KEY("ID"); 
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.PRESTATAIRE;             
CREATE MEMORY TABLE "PUBLIC"."PROPOSITION"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_13793305_AC0E_4474_9FFB_A136B57A0FB4" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_13793305_AC0E_4474_9FFB_A136B57A0FB4",
    "NOM" VARCHAR(100) NOT NULL,
    "PRIX" DOUBLE NOT NULL,
    "TYPE_PROPOSITION_ID" BIGINT NOT NULL
);          
ALTER TABLE "PUBLIC"."PROPOSITION" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_E3" PRIMARY KEY("ID"); 
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.PROPOSITION;             
CREATE MEMORY TABLE "PUBLIC"."RESEAU_SOCIAL"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_9A425097_BB7A_4FD4_9326_9BA7617FCF44" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_9A425097_BB7A_4FD4_9326_9BA7617FCF44",
    "NOM" VARCHAR(100) NOT NULL
); 
ALTER TABLE "PUBLIC"."RESEAU_SOCIAL" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_ED" PRIMARY KEY("ID");               
-- 8 +/- SELECT COUNT(*) FROM PUBLIC.RESEAU_SOCIAL;           
INSERT INTO "PUBLIC"."RESEAU_SOCIAL" VALUES
(1, 'Youtube'),
(2, 'Tiktok'),
(3, 'Instagram'),
(4, 'Facebook'),
(5, 'Twitter'),
(6, 'Linkedin'),
(7, 'Tumblr'),
(8, 'Mastodon');        
CREATE MEMORY TABLE "PUBLIC"."ROLE"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_58A7F34F_0A79_4678_8896_B906A748EC6A" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_58A7F34F_0A79_4678_8896_B906A748EC6A",
    "NOM" VARCHAR(50) NOT NULL
);           
ALTER TABLE "PUBLIC"."ROLE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2" PRIMARY KEY("ID");         
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.ROLE;    
CREATE MEMORY TABLE "PUBLIC"."STATUT"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_F282C155_AF28_450F_BEC9_16AE4F8C768B" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_F282C155_AF28_450F_BEC9_16AE4F8C768B",
    "NOM" VARCHAR(40) NOT NULL
);         
ALTER TABLE "PUBLIC"."STATUT" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_9" PRIMARY KEY("ID");       
-- 4 +/- SELECT COUNT(*) FROM PUBLIC.STATUT;  
INSERT INTO "PUBLIC"."STATUT" VALUES
(1, STRINGDECODE('Accept\u00e9')),
(2, STRINGDECODE('Refus\u00e9')),
(3, 'Actif'),
(4, 'Inactif');   
CREATE MEMORY TABLE "PUBLIC"."TYPE_DON"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_7ED5BF35_CF39_4F74_B081_DCB663EA7267" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_7ED5BF35_CF39_4F74_B081_DCB663EA7267",
    "NOM" VARCHAR(20) NOT NULL
);       
ALTER TABLE "PUBLIC"."TYPE_DON" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_6" PRIMARY KEY("ID");     
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.TYPE_DON;
INSERT INTO "PUBLIC"."TYPE_DON" VALUES
(1, 'Financier'),
(2, STRINGDECODE('Mat\u00e9riel'));
CREATE MEMORY TABLE "PUBLIC"."TYPE_PROPOSITION"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_FD833C9C_DD8F_4F99_AD19_74AD91720587" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_FD833C9C_DD8F_4F99_AD19_74AD91720587",
    "NOM" VARCHAR(80) NOT NULL
);               
ALTER TABLE "PUBLIC"."TYPE_PROPOSITION" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_D" PRIMARY KEY("ID");             
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.TYPE_PROPOSITION;        
INSERT INTO "PUBLIC"."TYPE_PROPOSITION" VALUES
(1, 'Service');               
ALTER TABLE "PUBLIC"."PERSONNE" ADD CONSTRAINT "PUBLIC"."UK_8L3J8SMOM1KAPEU3Y4F4D6G3G" UNIQUE("LOGIN");       
ALTER TABLE "PUBLIC"."LIEN_RESEAU_SOCIAL" ADD CONSTRAINT "PUBLIC"."UK_AV0QETEVAX86BF1WKMG2NB5K2" UNIQUE("URL");               
ALTER TABLE "PUBLIC"."RESEAU_SOCIAL" ADD CONSTRAINT "PUBLIC"."UK_LS1LGWPNI0GM0IU79K4B1TUSY" UNIQUE("NOM");    
ALTER TABLE "PUBLIC"."STATUT" ADD CONSTRAINT "PUBLIC"."UK_T5V7ON4J23MYDUUDG0OQ9Y47K" UNIQUE("NOM");           
ALTER TABLE "PUBLIC"."PROPOSITION" ADD CONSTRAINT "PUBLIC"."UK_T3APRH7EX8VBM8VOFHKTMNKN7" UNIQUE("NOM");      
ALTER TABLE "PUBLIC"."MEDIA" ADD CONSTRAINT "PUBLIC"."UK_GP9BUE980TMA9YHT908N9ALEB" UNIQUE("URL");            
ALTER TABLE "PUBLIC"."ROLE" ADD CONSTRAINT "PUBLIC"."UK_6U1K6PQQ7SUWEPWCFSKYMPY3S" UNIQUE("NOM");             
ALTER TABLE "PUBLIC"."ASSOCIATION" ADD CONSTRAINT "PUBLIC"."UK_BI61YIAN1LWRE9U9RLBAW4S63" UNIQUE("TELEPHONE");
ALTER TABLE "PUBLIC"."ASSOCIATION" ADD CONSTRAINT "PUBLIC"."UK_OS6FD6HNG87K1I4IKJOJBX20J" UNIQUE("NUMRNA");   
ALTER TABLE "PUBLIC"."ADRESSE" ADD CONSTRAINT "PUBLIC"."UKBDGMC0S44MKI3ETGTJHRCK1FU" UNIQUE("RUE", "COMPLEMENT", "CODE_POSTAL", "VILLE");     
ALTER TABLE "PUBLIC"."MEDIA" ADD CONSTRAINT "PUBLIC"."UK_EFCDXUJENO0GKQUU69EXNDDTO" UNIQUE("PATH");           
ALTER TABLE "PUBLIC"."CATEGORIE" ADD CONSTRAINT "PUBLIC"."UK_89Y3D23IA9RUHFHDMYA9ASPQ7" UNIQUE("NOM");        
ALTER TABLE "PUBLIC"."TYPE_PROPOSITION" ADD CONSTRAINT "PUBLIC"."UK_8HFT0CSJ94N3XQ5ORDPDE9US3" UNIQUE("NOM"); 
ALTER TABLE "PUBLIC"."ASSOCIATION" ADD CONSTRAINT "PUBLIC"."UK_PK6QQF0X3MUHJ7Q5CXSRRRTQ1" UNIQUE("MAIL");     
ALTER TABLE "PUBLIC"."MEDIA" ADD CONSTRAINT "PUBLIC"."UK_5Y3TRPPE86PSJ2VYW7334Y2XS" UNIQUE("TITRE");          
ALTER TABLE "PUBLIC"."ASSOCIATION" ADD CONSTRAINT "PUBLIC"."UK_IYSSU3KV05DSMEQERC9YFORVJ" UNIQUE("NOM");      
ALTER TABLE "PUBLIC"."PERSONNE" ADD CONSTRAINT "PUBLIC"."UK_LIF11UG7PQKDIMUK0BEONBFNG" UNIQUE("EMAIL");       
ALTER TABLE "PUBLIC"."ADHESION" ADD CONSTRAINT "PUBLIC"."UKTDKWUFYNBMKV2RM4DQXA21L2D" UNIQUE("ASSOCIATION_ID", "PERSONNE_ID");
ALTER TABLE "PUBLIC"."TYPE_DON" ADD CONSTRAINT "PUBLIC"."UK_1G00PHVK71Y9GHBK9CIPFM3S1" UNIQUE("NOM");         
ALTER TABLE "PUBLIC"."PRESTATAIRE" ADD CONSTRAINT "PUBLIC"."UK_MVXFN94YTSR9QBISKRN6BHC86" UNIQUE("NOM");      
ALTER TABLE "PUBLIC"."PRESTATAIRE" ADD CONSTRAINT "PUBLIC"."UK_BB7JAHRD8OM07KOARUO6S24GU" UNIQUE("EMAIL");    
ALTER TABLE "PUBLIC"."CONTACT_PROFESSIONNEL" ADD CONSTRAINT "PUBLIC"."FK91VBTWFPHCIBWH2FOQ5HDD5Y8" FOREIGN KEY("CONTACT_ID") REFERENCES "PUBLIC"."PRESTATAIRE"("ID") NOCHECK; 
ALTER TABLE "PUBLIC"."OFFRE" ADD CONSTRAINT "PUBLIC"."FK7D2UJE6PIPXWJFXAYPIQ08WDR" FOREIGN KEY("PROPOSITION_ID") REFERENCES "PUBLIC"."PROPOSITION"("ID") NOCHECK;             
ALTER TABLE "PUBLIC"."PROPOSITION" ADD CONSTRAINT "PUBLIC"."FKQYI8P258GA7RRWSTE8OY84NOI" FOREIGN KEY("TYPE_PROPOSITION_ID") REFERENCES "PUBLIC"."TYPE_PROPOSITION"("ID") NOCHECK;             
ALTER TABLE "PUBLIC"."ASSOCIATION" ADD CONSTRAINT "PUBLIC"."FKL90JW5NSTABN1D86OT671VDG0" FOREIGN KEY("STATUT_ID") REFERENCES "PUBLIC"."STATUT"("ID") NOCHECK; 
ALTER TABLE "PUBLIC"."CONTACT_PROFESSIONNEL" ADD CONSTRAINT "PUBLIC"."FKDTW5MY6BDYS9DBH03QJ9NOT2N" FOREIGN KEY("ASSOCIATION_ID") REFERENCES "PUBLIC"."ASSOCIATION"("ID") NOCHECK;             
ALTER TABLE "PUBLIC"."OFFRE" ADD CONSTRAINT "PUBLIC"."FKIWCH92SVCHHTO5WOUEDHJHBMW" FOREIGN KEY("ASSOCIATION_ID") REFERENCES "PUBLIC"."ASSOCIATION"("ID") NOCHECK;             
ALTER TABLE "PUBLIC"."PERSONNE" ADD CONSTRAINT "PUBLIC"."FKJECJ1A4KAJ1Q22T2BDRF74GF3" FOREIGN KEY("ROLE_ID") REFERENCES "PUBLIC"."ROLE"("ID") NOCHECK;        
ALTER TABLE "PUBLIC"."ADHESION" ADD CONSTRAINT "PUBLIC"."FK6VXV0B1D377PNHMTBD5T1MTUE" FOREIGN KEY("ASSOCIATION_ID") REFERENCES "PUBLIC"."ASSOCIATION"("ID") NOCHECK;          
ALTER TABLE "PUBLIC"."DON" ADD CONSTRAINT "PUBLIC"."FK8KEPX3FVJG51KQCIR9Q9NJ4HX" FOREIGN KEY("ASSOCIATION_ID") REFERENCES "PUBLIC"."ASSOCIATION"("ID") NOCHECK;               
ALTER TABLE "PUBLIC"."MEDIA" ADD CONSTRAINT "PUBLIC"."FKNG84MJK8470YNR5TGITGAFL5E" FOREIGN KEY("ASSOCIATION_ID") REFERENCES "PUBLIC"."ASSOCIATION"("ID") NOCHECK;             
ALTER TABLE "PUBLIC"."PERSONNE" ADD CONSTRAINT "PUBLIC"."FK47VWC3EXI8YC0PHF8J6UPHG4D" FOREIGN KEY("STATUT_ID") REFERENCES "PUBLIC"."STATUT"("ID") NOCHECK;    
ALTER TABLE "PUBLIC"."ASSOCIATION" ADD CONSTRAINT "PUBLIC"."FKRMS58XF4N4371DCJJQL25JHE5" FOREIGN KEY("CATEGORIE_ID") REFERENCES "PUBLIC"."CATEGORIE"("ID") NOCHECK;           
ALTER TABLE "PUBLIC"."LIEN_RESEAU_SOCIAL" ADD CONSTRAINT "PUBLIC"."FKDI7Y7UJ3OFK2FVNYCTYOINWOT" FOREIGN KEY("RESEAU_SOCIAL_ID") REFERENCES "PUBLIC"."RESEAU_SOCIAL"("ID") NOCHECK;            
ALTER TABLE "PUBLIC"."MEDIA" ADD CONSTRAINT "PUBLIC"."FK5PELR55TSIONMLT62J7SAPOJ8" FOREIGN KEY("PERSONNE_ID") REFERENCES "PUBLIC"."PERSONNE"("ID") NOCHECK;   
ALTER TABLE "PUBLIC"."PRESTATAIRE" ADD CONSTRAINT "PUBLIC"."FKI7UAPYSGUM1TBKYWD9SL6VGFV" FOREIGN KEY("STATUT_ID") REFERENCES "PUBLIC"."STATUT"("ID") NOCHECK; 
ALTER TABLE "PUBLIC"."DON" ADD CONSTRAINT "PUBLIC"."FK7XQQSY58Y0G2JBRI4KAKJ634O" FOREIGN KEY("PERSONNE_ID") REFERENCES "PUBLIC"."PERSONNE"("ID") NOCHECK;     
ALTER TABLE "PUBLIC"."DON" ADD CONSTRAINT "PUBLIC"."FK1YQLASBF9RAJ5ERY94QR9MDG7" FOREIGN KEY("TYPE_DON_ID") REFERENCES "PUBLIC"."TYPE_DON"("ID") NOCHECK;     
ALTER TABLE "PUBLIC"."ASSOCIATION" ADD CONSTRAINT "PUBLIC"."FKBQYP50TPTM2IR85AI8UEAHW7O" FOREIGN KEY("ADRESSE_ID") REFERENCES "PUBLIC"."ADRESSE"("ID") NOCHECK;               
ALTER TABLE "PUBLIC"."PRESTATAIRE" ADD CONSTRAINT "PUBLIC"."FKNJPH05DTGEG5OB8V0JV0VX0FH" FOREIGN KEY("ADRESSE_ID") REFERENCES "PUBLIC"."ADRESSE"("ID") NOCHECK;               
ALTER TABLE "PUBLIC"."PERSONNE" ADD CONSTRAINT "PUBLIC"."FK64WYPB6GO3KIEGRP8NTDF7YWP" FOREIGN KEY("ADRESSE_ID") REFERENCES "PUBLIC"."ADRESSE"("ID") NOCHECK;  
ALTER TABLE "PUBLIC"."ADHESION" ADD CONSTRAINT "PUBLIC"."FKHRDO8LX016DN1A3OT095JO964" FOREIGN KEY("PERSONNE_ID") REFERENCES "PUBLIC"."PERSONNE"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."DON" ADD CONSTRAINT "PUBLIC"."FKCOBXNQ6LQ8UAMG6F3PSJWEUR3" FOREIGN KEY("STATUT_ID") REFERENCES "PUBLIC"."STATUT"("ID") NOCHECK;         
ALTER TABLE "PUBLIC"."LIEN_RESEAU_SOCIAL" ADD CONSTRAINT "PUBLIC"."FKH0KBBW8UY35E2XPTHHUGA8MY7" FOREIGN KEY("ASSOCIATION_ID") REFERENCES "PUBLIC"."ASSOCIATION"("ID") NOCHECK;
