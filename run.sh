#!/bin/bash

if [ ! -d "build/classes" ]; then
    echo "🚫  Projeto não compilado. Execute ./compile.sh primeiro!"
    exit 1
fi

echo "⚙️  Executando aplicação:"
java -cp build/classes:lib/postgresql-42.2.2.jre7.jar Principal
