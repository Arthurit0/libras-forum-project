#!/bin/bash

echo "🔨 Compilando projeto..."
mkdir -p build/classes

javac -cp lib/postgresql-42.2.2.jre7.jar \
      src/*.java \
      -d build/classes

if [ $? -eq 0 ]; then
    echo "✅ Compilação concluída!"
else
    echo "❌ Erro na compilação"
    exit 1
fi
