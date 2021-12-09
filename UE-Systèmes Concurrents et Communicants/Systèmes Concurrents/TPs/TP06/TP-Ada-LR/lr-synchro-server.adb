with Ada.Text_IO; use Ada.Text_IO;
with Ada.Exceptions;

-- Version simple : exclusion mutuelle pour toutes les opérations
--  fournit une ossature pour l'approche "service"

package body LR.Synchro.Server is
   
   function Nom_Strategie return String is
   begin
      return "Stratégie : Server";
   end Nom_Strategie;

   task LectRedTask is
      entry Demander_Lecture;
      entry Demander_Ecriture;
      entry Terminer_Lecture;
      entry Terminer_Ecriture;
   end LectRedTask;

   task body LectRedTask is
         nbLecteurs : Natural := 0;
         nbEcriteurs : Natural := 0;
         nbLectAtt : Natural := 0;
         nbEcriAtt : Natural := 0;
         isWriting : Boolean := false;

   begin
      loop
      	select
            -- 当 读者为0 时
            when nbLecteurs = 0 =>
            -- 读 操作
            accept Demander_Lecture;
               nbLecteurs := nbLecteurs + 1;
         or 
            -- 当 写者为0 && 读者为0 时
            when nbEcriteurs = 0 and nbLecteurs = 0 =>
            -- 写 操作
            accept Demander_Ecriture;
               nbEcriteurs := nbEcriteurs + 1;
         or
            when nbEcriteurs > 0 =>
            accept Terminer_Ecriture;
               nbEcriteurs := nbEcriteurs - 1;
         or
            when nbLecteurs > 0 =>
            accept Terminer_Lecture;
               nbLecteurs := nbLecteurs - 1;
         or
            terminate;
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

end LR.Synchro.Server;