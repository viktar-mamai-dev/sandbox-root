--This script is suitable for generating 10000 records for each of the 6 tables
--For data generation for table news we use following algorithm:
-- 1. specify 10 different titles, short texts, full texts, creation dates
-- 2. use decart multiplication of such sets to provide 10000 records
-- Data generation for tables tags and author, news_tag and news_author is too simple
--Each comment has different news, so each news item will have only one comment
--Note for simplifying we use only one single comment text

set define off;
set serveroutput; --for dbms output

  CREATE OR REPLACE PROCEDURE GEN10000 IS
  type short_text_type is varray(10) of nvarchar2(100);
  type title_type is varray(10) of nvarchar2(100);
  type full_text_type is varray(10) of nvarchar2(2000);
  type creation_date_type is varray(10) of DATE;
  short_text_array short_text_type;
  title_array title_type;
  full_text_array full_text_type;
  creation_date_array creation_date_type;
  idx1 number(20):=1;
BEGIN
  short_text_array:=short_text_type(
    'MPs have overwhelmingly backed plans for a referendum on the UK', 
    'In a world obsessed with cheap, mass-produced products, Levere', 
    'Every year, more than nine million students in China sit a high-stakes examination the gaokao',
    'Barcelona coach has ended speculation over his future at the club by extending his contract',
    'Stan Wawrinka ended home favourite Jo-Wilfried Tsonga hopes in a semi-final at the French Open',
    'Mo Farah set the worlds quickest 10,000m time of 2015 at a race in the United States',
    'Dont ask Mohed Altrad how old he is. He may be a billionaire, but he doesnt know his age. No rec',
    'Mainland Chinese shares reversed earlier losses to head higher, despite US index provider',
    's3', 
    's4'
  );
  full_text_array:=full_text_type(
    'The vote, which followed the first debate on the EU Referendum Bill, means the legislation moves to the next stage of its progress through Parliament. Foreign Secretary Philip Hammond said a generation had been denied a say on the UKs place in Europe, and the public must now have the final say.',
    'The world of bamboo bike makers is small: more asteroid than Jupiter. Carbon-fibre bike frame pioneer Craig Calfee, owner of California-based Calfee Design, produces bamboo bikes; he also teaches people in Ghana how to make bamboo bikes and created a company to sell them called Bamboosero. Panda Bicycles and Boo Bikes in Fort Collins, Colorado; Stalk Bicycles in Oakland, California; and WebbWorks in Greenville, South Carolina, round out the enclave of US-based bambooistas',
    'The gaokao is seen as a make-or-break opportunity, especially for those from poorer families, in a country where a degree is essential for a good job. The exam is tightly policed, but the pressure means cheating is perhaps inevitable',
    'The 45-year-old led the Spanish giants to Champions League, La Liga, and Copa del Rey titles in his debut season. Enrique refused to say whether he would remain at the Nou Camp after Saturdays 3-1 win against Juventus sealed the second treble in the clubs history',
    'Wawrinka, 30, will face top seed Novak Djokovic or Britains Andy Murray in the final on Sunday. Tsonga had hoped to become Frances first male champion since Yannick Noah in 1983',
    'Farah won in a time of 26 minutes 50.97 seconds at a Diamond League meeting in Eugene, Oregon, on Friday, Farah, who won 5,000m and 10,000m gold at London 2012, was more than four seconds outside his personal best',
    'Mr Altrad told me his astonishing story in the unlikely surroundings of one of the poshest hotels in that nest of posh, Monte Carlo', 
    'Even though MSCI said the decision around China-listed shares would remain on its 2016 review list, the delay shows that global asset managers still have reservations about Beijing',
    'f3', 
    'f4'
  );
  title_array:=title_type(
    'EU referendum: MPs support plan for say on Europe', 
    'Erba Cycles raises grass-fed steers', 
    'Chinas gaokao: High stakes for national exam', 
    'Coach Luis Enrique extends contract to 2017',
    'French Open: Stan Wawrinka beats Jo-Wilfried Tsonga in semis',
    'Mo Farah runs fastest 10,000m of 2015, then criticises pacemakers',
    'Entrepreneur of the year: a Bedouin turned businessman', 
    'Mainland China shares reverse losses despite MSCI delay', 
    't3', 
    't4'
  );
  creation_date_array:=creation_date_type(
    to_date('01-01-2008','DD-MM-YYYY'),
    to_date('02-02-2009','DD-MM-YYYY'),
    to_date('03-03-2008','DD-MM-YYYY'),
    to_date('04-04-2010','DD-MM-YYYY'),
    to_date('05-05-2009','DD-MM-YYYY'),
    to_date('06-06-2011','DD-MM-YYYY'),
    to_date('07-07-2010','DD-MM-YYYY'),
    to_date('08-08-2012','DD-MM-YYYY'),
    to_date('09-09-2011','DD-MM-YYYY'),
    to_date('10-10-2013','DD-MM-YYYY')
  );
  DELETE FROM COMMENTS;
  DELETE FROM NEWS_TAG;
  DELETE FROM NEWS_AUTHOR;
  DELETE FROM AUTHOR;
  DELETE FROM TAG;
  DELETE FROM NEWS;

  for i1 in short_text_array.first..short_text_array.last
  loop
    for i2 in title_array.first..title_array.last
    loop
      for i3 in full_text_array.first..full_text_array.last
      loop
        for i4 in creation_date_array.first..creation_date_array.last
        loop
          idx1:=(i1-1)*1000 + (i2-1)*100 + (i3-1)*10 + i4;     
          INSERT INTO NEWS(news_id, TITLE, short_text, full_text, creation_date, modification_date) 
          VALUES (idx1, title_array(i2), short_text_array(i1), full_text_array(i3), creation_date_array(i4), creation_date_array(i4));
          --dbms_output.put_line('value of idx news id: ' || idx1);
        end loop;
      end loop;
    end loop;
  end loop;
  commit;
  
  
  for i in 1..10000
  loop
    INSERT INTO author (author_id, name, expired) values (i, 'author'||i, null);
    Insert into NEWS_AUTHOR (NEWS_ID, AUTHOR_ID) values (i, i);
    INSERT INTO tag (tag_id, tag_name) VALUES (i, 'tag'||i);
    Insert into NEWS_TAG (NEWS_ID,TAG_ID) values (i,i);
    
    idx1 := mod(i, 10) + 1;
    Insert into COMMENTS (COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) values (i, 'ServletConfig is implemented by the servlet container to initialize a single servlet using init()',
     creation_date_array(idx1) , i);
  end loop;
  commit;
END GEN10000;

/

exec gen10000();