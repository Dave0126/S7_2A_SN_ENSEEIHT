with Ada.Text_IO; use Ada.Text_IO;
with Ada.Exceptions;

-- Lecteurs concurrents, approche automate. Pas de politique d'accès.
package body LR.Synchro.Basique is
   
   function Nom_Strategie return String is
   begin
      return "Automate, lecteurs concurrents, sans politique d'accès";
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
         nb : Natural := 0;

      begin
         loop
            -- TODO
            select
               when etat = LIBRE or etat = READING =>
                  accept Demander_Lecture;
                  etat := READING;
                  nb := nb + 1;
               or
               when etat = LIBRE or etat = WRITING =>
                  accept Demander_Ecriture;
                  etat := WRITING;
                  nb := nb + 1;
               or
               when etat = READING =>
                  accept Terminer_Lecture;
                  nb := nb - 1;
                  if nb = 0 then
                     etat := LIBRE;
                  end if;
               or
               when etat = WRITING =>
                  accept Terminer_Ecriture;
                  nb := nb - 1;
                  if nb = 0 then
                     etat := LIBRE;
                  end if;       
            end select;
         end loop;
      exception
         when Error: others =>
            Put_Line("**** LectRedTask: exception: " & Ada.Exceptions.Exception_Information(Error));
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

end LR.Synchro.Basique;