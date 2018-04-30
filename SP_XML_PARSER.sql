create or replace procedure SP_XML_PARSER is

  x XMLType := XMLType(' 

<person>   

<row>       

<name>Tom</name>       

<Address>          

<LocalAddress>           

<State>California</State>           

<City>Los angeles</City>          

</LocalAddress>          

<LocalAddress>           

<State>California1</State>           

<City>Los angeles1</City>          

</LocalAddress>       

</Address>   

</row>   

<row>       

<name>Jim</name>       

<Address>    

<LocalAddress> 

<State>Massachusetts</State>

<City>Boston</City>      

</LocalAddress>

<LocalAddress> 

<State>Houston</State>

<City>Louisiana</City>      

</LocalAddress>  

</Address>   

</row>

</person>');

BEGIN

  FOR r IN (SELECT ExtractValue(Value(p), '/row/name/text()') AS name,

                   Extract(Value(p), '/row/Address') As Address

              FROM TABLE(XMLSequence(Extract(x, '/person/row'))) p) LOOP

    DBMS_OUTPUT.put_line(r.name || ' ');

    FOR row1 IN (SELECT ExtractValue(Value(l), '/LocalAddress/City/text()') AS city,

                        ExtractValue(Value(l), '/LocalAddress/State/text()') AS state

                   FROM TABLE(XMLSequence(Extract(r.Address,

                                                  '/Address/LocalAddress'))) l) LOOP

    

      DBMS_OUTPUT.put_line(row1.city || ' '||row1.state);

      -- do whatever you want with r.name, r.state, r.city

    END LOOP;

    -- do whatever you want with r.name, r.state, r.city

  END LOOP;

END;