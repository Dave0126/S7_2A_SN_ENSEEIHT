with Ada.Text_IO; use Ada.Text_IO;
with Ada.Exceptions;

-- Version simple : exclusion mutuelle pour toutes les opérations
--  fournit une ossature pour l'approche "service"

package body LR.Synchro.PrioRedacteur is
   
   function Nom_Strategie return String is
   begin
      return "Stratégie : PrioRedacteur";
   end Nom_Strategie;

   task LectRedTask is
      entry Demander_Lecture;
      entry Demander_Ecriture;
      entry Terminer_Lecture;
      entry Terminer_Ecriture;
   end LectRedTask;

   task body LectRedTask is
   type ETAT2 is (READING, WRITING, LIBRE);
         etat : ETAT2 := LIBRE;

         nbLecteurs : Natural := 0;
         nbEcriteurs : Natural := 0;
         nbLectAtt : Natural := 0;
         nbEcriAtt : Natural := 0;
         nb : Natural := 0;
   begin
      loop
      	case etat is
         when LIBRE =>
            select
               -- 当没有 写请求 时
               when (Demander_Ecriture'count = 0) =>
               -- 可读
               accept Demander_Lecture;
                  etat := READING;
                  nb := 1;
            or
               -- 或者 可写
               accept Demander_Ecriture;
                  etat := WRITING;
            end select;

         when READING =>
            select
            -- 在 READING 状态中，有人正在读。
               -- 当没有 写请求 时
               when (Demander_Ecriture'count = 0) =>
               -- 可读
               accept Demander_Lecture;
                  etat := READING;
                  nb := nb + 1;
            or
               -- 或者 结束读
               accept Terminer_Lecture;
                  nb := nb - 1;
               if nb = 0 then
                  -- 当读的人数为0时， 状态为LIBRE
                  etat := LIBRE;
               end if;
            end select;

         when WRITING =>
         -- 在 WRITING 状态中，有人正在写
            select
               -- 结束写
               accept Terminer_Ecriture;
                  -- 直接将状态改为LIBRE
                  etat := LIBRE;
            end select;
         end case;         
      end loop;
   end LectRedTask;

   procedure Demander_Lecture is
   begin
      LectRedTask.Demander_Lecture;
   end Demander_Lecture;

   procedure Demander_Ecriture is
   begin
      LectRedTask.Demander_Ecriture;
   end Demander_Ecriture;

   procedure Terminer_Lecture is
   begin
      LectRedTask.Terminer_Lecture;
   end Terminer_Lecture;

   procedure Terminer_Ecriture is
   begin
      LectRedTask.Terminer_Ecriture;
   end Terminer_Ecriture;

end LR.Synchro.PrioRedacteur;