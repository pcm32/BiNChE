#run with (example): bsub -I -n 8 -R "span[hosts=1]" -M 20000 -R "rusage[mem=25000]" /homes/hastings/deploy/binche/runPreProcessObo.sh

ant -Doutputfile="output_roles.log" -Darg0="files/chebi.obo"  -Darg1="files/chebi_roles.obo" -Darg2="false" -Darg3="false" -Darg4="CHEBI:50906" -Darg5="rdfs:label" 
rm files/chebi_roles.obo.temp 
ant -Doutputfile="output_strucs.log" -Darg0="files/chebi.obo"  -Darg1="files/chebi_chems.obo" -Darg2="false" -Darg3="false" -Darg4="CHEBI:24431" -Darg5="rdfs:label;InChI"
rm files/chebi_chems.obo.temp  
ant -Doutputfile="output_strucandroles.log" -Darg0="files/chebi.obo"  -Darg1="files/chebi_chemandroles.obo" -Darg2="true" -Darg3="true" -Darg4="CHEBI:50906;CHEBI:24431" -Darg5="rdfs:label;InChI" -Darg6="http://purl.obolibrary.org/obo/chebi#has_role"
rm files/chebi_chemandroles.obo.temp  
mv files/chebi_chemandroles.txt files/chebi_roles.anno  

