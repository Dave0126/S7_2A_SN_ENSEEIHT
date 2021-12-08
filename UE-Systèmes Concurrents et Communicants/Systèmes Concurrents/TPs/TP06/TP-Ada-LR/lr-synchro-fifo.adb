with Ada.Text_IO; use Ada.Text_IO;
with Ada.Exceptions;

-- Version simple : exclusion mutuelle pour toutes les opérations
--  fournit une ossature pour l'approche "service"

package body LR.Synchro.FIFO is
   
   function Nom_Strategie return String is
   begin
      return "Stratégie : FIFO";
   end Nom_Strategie;
   type state is (Read, Write);
   task LectRedTask is
      entry Demande(c : state);
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
            when nbEcriteurs = 0 =>
               accept Demander(c : state) do
                  if (c = Read) then
                     -- READING
                     nbLecteurs := nbLecteurs + 1;
                  else if (c = Write) then
                     -- WRITING
                     loop nbLecteurs > 0
                        accept Terminer_Lecture;
                        nbLecteurs := nbLecteurs - 1;
                     end loop;
                     nbEcriteurs := nbEcriteurs + 1;
                  end if;
               end Demander;
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
      LectRedTask.Demander(Read);
   end Demander_Lecture;

   procedure Demander_Ecriture is
   begin
      LectRedTask.Demander(Write);
   end Demander_Ecriture;

   procedure Terminer_Lecture is
   begin
      LectRedTask.Terminer_Lecture;
   end Terminer_Lecture;

   procedure Terminer_Ecriture is
   begin
      LectRedTask.Terminer_Ecriture;
   end Terminer_Ecriture;

end LR.Synchro.FIFO;