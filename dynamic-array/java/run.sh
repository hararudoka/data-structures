if ! javac -d ./dist Main.java DynamicList.java; then
    echo "Compilation failed. Exiting script."
    exit 1
fi
cd dist
java Main DynamicList
