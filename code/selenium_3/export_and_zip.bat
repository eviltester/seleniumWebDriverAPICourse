# this is used to automate the creation of the zip file
# of code for the course
#
del .\export\code.zip
mkdir .\export
svn export ./ ./export/code
"C:\Program Files\7-Zip\7z.exe" a -r ./export/code.zip ./export/code/*.*
rmdir /S /Q .\export\code