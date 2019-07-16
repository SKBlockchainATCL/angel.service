@echo on

:: https://github.com/Redocly/redoc#redoc-options-object
redoc-cli bundle -o ../../docs/rest-api.html  ../../target/generated/swagger/api.json --options.pathInMiddlePanel