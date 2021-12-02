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
   begin
      loop
      	select
            when nbEcriteurs + nbEcriAtt > 0 =>
               nbLectAtt := nbLectAtt + 1;
               nbLectAtt := nbLectAtt - 1;
               
    	      or
            when 
			
            or
        	
         end select;
        -- une fois une opération acceptée, on accepte uniquement sa terminaison
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