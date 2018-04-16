CREATE OR REPLACE PROCEDURE load_file(pfname VARCHAR2) IS
  l_size     number;
  l_file_ptr bfile;
  l_blob     blob;
begin
  l_file_ptr := bfilename('IMGDIR', pfname);
  dbms_lob.fileopen(l_file_ptr);
  l_size := dbms_lob.getlength(l_file_ptr);
  insert into images
    (img_id, icon)
  values
    (image_id.nextval, empty_blob())
  returning icon into l_blob;
  dbms_lob.loadfromfile(l_blob, l_file_ptr, l_size);
  commit;
  dbms_lob.close(l_file_ptr);
end;



--CREATE SEQUENCE image_id START WITH 1 INCREMENT BY 1 NOMAXVALUE;

--CREATE TABLE images(img_id NUMBER, icon BLOB);
/
