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
         etatCurrent : ETAT2 := LIBRE;

         nbLecteurs : Natural := 0;
         nbEcriteurs : Natural := 0;
         nbLectAtt : Natural := 0;
         nbEcriAtt : Natural := 0;
         nb : Natural := 0;
   begin
      loop
      	case etatCurrent is
         when LIBRE =>
            select
               when (Demander_Ecriture'count = 0) =>
               accept Demander_Lecture;
                  etatCurrent := READING;
                  nb := 1;
            or
               accept Demander_Ecriture;
                  etatCurrent := WRITING;
            end select;

         when READING =>
            select
               when (Demander_Ecriture'count = 0) =>
               accept Demander_Lecture;
                  etatCurrent := READING;
                  nb := nb + 1;
            or
               accept Terminer_Lecture;
                  nb := nb - 1;
               if nb = 0 then
                  etatCurrent := LIBRE;
               end if;
            end select;

         when WRITING =>
            select
               accept Terminer_Ecriture;
                  etatCurrent := LIBRE;
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