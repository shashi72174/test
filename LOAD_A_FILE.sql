CREATE OR REPLACE PROCEDURE LOAD_A_FILE
   ( file_name  VARCHAR2
    -- author     VARCHAR2
   ) IS
  l_bfile   BFILE;
  l_clob    CLOB;
BEGIN
   l_bfile := bfilename('INPUT_FILES', file_name);
   dbms_output.put_line('INPUT_FILES' || ' '|| file_name);
   dbms_lob.fileopen( l_bfile);
   IF (dbms_lob.fileexists(l_bfile) = 1) THEN
      dbms_output.put_line('File Exists');
      INSERT INTO my_table T
        VALUES (loadtest_seq.NEXTVAL,
                USER,
                sysdate,
               EMPTY_CLOB()
      ) RETURN text INTO l_clob;
      --L_BFILE := bfilename('clobdata', file_name);
      dbms_lob.loadfromfile( l_clob, l_bfile, dbms_lob.getlength(l_bfile) );
      dbms_lob.fileclose( l_bfile );
      COMMIT;
   ELSE
     dbms_output.put_line('File does not exist');
   END IF;
   EXCEPTION 
        WHEN others THEN 
      dbms_output.put_line(SQLERRM);
END;
/
