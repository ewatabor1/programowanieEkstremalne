# Config Files

Guide to configuration parameter files.

## config.json

Main file for storing configuration parameters.

### server

#### server.port (integer)

Port for the webserver to run on

#### server.servlet.context-path (string)

Path for the api to be accessible by on the webserver

### database

#### database.driver (string)

Driver type for the database

#### database.url (string)

URL to the database

#### database.username (string)

Username to access to the database

#### database.username (string)

Password to access to the database

### initial_products (string)

Path to .json providing initial products to be inserted into database on startup

### initial_recipes

Path to .json providing initial recipes to be inserted into database on startup

## initial_products.json

JSON file providing list of products with attributes in JSON format e.g.

```
[
  {
    "name": "Masło",
    "kcal": 670,
    "expiryDate": "2021-06-05"
  },
  {
    "name": "Chleb",
    "kcal": 280,
    "expiryDate": "2021-05-25"
  }
]
```

## initial_recipes.json

JSON file providing list of recipes in JSON format e.g.
```
[
  {
    "name": "Kanapka z masłem",
    "ingredients": [
      {
        "productName": "Masło",
        "amount": 0.1
      },
      {
        "productName": "Chleb",
        "amount": 1.5
      }
    ]
  },
  {
    "name": "Parówki z wody",
    "ingredients": [
      {
        "productName": "Parówki",
        "amount": 5
      },
      {
        "productName": "Woda",
        "amount": 0.5
      }
    ]
  }
]
```