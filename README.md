Server hosts on port 8080

"Homepage":
  http://localhost:8080

Viewing all todo items:
  http://localhost:8080/all

Viewing unfinished items:
  http://localhost:8080/todo

Viewing finished items:
  http://localhost:8080/done

SEARCH ITEMS CONTAINING SPECIFIC STRING:
  Send GET request to http://localhost:8080/search with desired string as content value
  Example:

  GET http://localhost:8080/search
  //body
  {
    content: "Thing you're looking for"
  }

ADDING ITEMS:
  Todo items are added in json format, with a content in body.
  Example:
    POST http://localhost:8080
    //body
      {
        content: "This is an example todo item"
      }

DELETING ITEMS:
  Send delete request to item ID URL.
  Example:
    DELETE http://localhost:8080/{idToDelete}


UPDATING ITEMS:
  Send PUT request to item ID URL with updated value in body (either content, isDone, or both).
  Example:

  PUT http://localhost:8080/{idToUpdate}
  //body
    {
      isDone: true
    }
or
    {
      content: "This is going to change the content of the todo item"
    }
or
    {
      content: "This is going to change the content of the todo item"
      isDone: true
    }
