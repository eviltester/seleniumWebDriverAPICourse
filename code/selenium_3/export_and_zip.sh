# this is used to automate the creation of the zip file
# of code for the course
# sh export_and_zip.sh

pandoc ./docs/readme.txt -f markdown -s -o ./docs/readme.pdf --toc
rm -f ./export/code.zip
mkdir -p ./export
mkdir -p ./export/code

cp ./code/pom.xml ./export/code/pom.xml
cp -a ./docs/. ./export/code/docs/
cp -a ./code/src/. ./export/code/src/
cd ./export
zip -r ./code.zip ./code/*
cd ..
rm -rf ./export/code

mkdir -p ./releases/$(date +"%Y%m%d")
cp ./docs/readme.pdf ./releases/$(date +"%Y%m%d")/
cp ./docs/readme.pdf ./releases/$(date +"%Y%m%d")/readme$(date +"%Y%m%d").pdf
cp ./docs/readme.txt ./releases/$(date +"%Y%m%d")/
cp ./docs/readme.txt ./releases/$(date +"%Y%m%d")/readme$(date +"%Y%m%d").txt
cp ./export/code.zip ./releases/$(date +"%Y%m%d")/
cp ./export/code.zip ./releases/$(date +"%Y%m%d")/code$(date +"%Y%m%d").zip


