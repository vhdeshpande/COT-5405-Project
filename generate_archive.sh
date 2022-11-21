#!/usr/bin/env bash
DIR_NAME="DeshpandeVaibhaviPuranikPurvaKantariyaBhakti"
REPORT_FILE="Report.pdf"

echo "Removing Directory ${DIR_NAME} ..."
rm -rf ${DIR_NAME}

echo "Removing Archive Directory ${DIR_NAME}.zip ..."
rm -rf ${DIR_NAME}

echo "Creating New Directory $DIR_NAME ..."
mkdir -p ${DIR_NAME}

echo "Copying source files to Directory $DIR_NAME ..."
cp *.java ${DIR_NAME}

echo "Copying source files to Directory $DIR_NAME ..."
cp *.txt ${DIR_NAME}

echo "Copying makefile files to Directory $DIR_NAME ..."
cp Makefile ${DIR_NAME}

echo "Copying Report to Directory $DIR_NAME ..."
cp ${REPORT_FILE} ${DIR_NAME}

echo "Creating Archive $DIR_NAME.zip ..."
zip -qr "${DIR_NAME}.zip" ${DIR_NAME}

echo "Removing the directory ${DIR_NAME}"
rm -rf ${DIR_NAME}

echo "Done"