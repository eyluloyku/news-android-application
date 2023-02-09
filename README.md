# news-android-application
# Objective

**Creating a simple Android mobile application for the News API.**

# Functionality

**Main Activity**

- When starts, display tabs for each news category. You can get the category list using “Get All
    News Categories“ service.
- When user selects a category using tabs, display the list of news accordingly (image, title and
    text of news). 
- When user swipes right or left, news list must change along with the category.
- Display a progress spinner while downloading data.
- Show home icon at the left of action bar. 



**News Details Activity**

- When a news item is selected from the MainActivity, app navigates to News Details Activity
    displaying the details of the news.


- You can pass the selected news info here using an Intent object from MainActivity or pass the
    news id and get use of the “Get News By Id” service. You will need to download the image
    seperately using the “imagelink” fields returned by the service.
- Appbar display Back Button to navigate to the MainActivity and a menu button to navigate to
    Comments activity. 
- Display the image, title, date and text of the news, the **screen must be scrollable**.

**Comments Activity**

- When the “View Comments” button clicked from News Details Activity, this activity is
    displayed. You can pass the news id from the previous activity and get use of the “Get
    Comments By News Id” service. 
- Display a progress dialog or progress bar while downloading data. 
- Display a RecyclerView containing comments, display name of the comment owner and their
    message in each row.
- Appbar contains Back button and a button to navigate to Post Comment activity. 

**Post Comment Activity**

- Appbar displays a Back button to navigate to Comments activity.


- Screen contains an EditText field for comment owner’s name, a multiline EditText for
    comment owner’s message and a button for posting action. 
- When the Post Comment button is pressed, get use of the “Post Comment” service to post the
    comment to the web service. Display a progress dialog while uploading the data. 
- If name or message is empty, inform user with a textview and do not post the request. 
- If the post operation completes successfully, display the previous “Comments” activity
    refreshing it with the last comment posted. 

# Application Schema

![image](https://user-images.githubusercontent.com/116841987/217854931-155a0572-041a-4a63-8b0f-9b24c65dfb38.png)


# The News Service Documentation

- The news agency provides you a Restful web service, the details are below.
- All requests returns a JSON response with the folowing fields:
    o serviceMessageCode : if “0”, request failed. If “1” request is successful
    o serviceMessageText: “SUCCESS” for successful calls, “FAIL” for failed calls and
       exception message for exceptions.
    o items: List of items returned, null if not valid
- In the service addresses, parts stated between curly parantheses (“{}”) are the fields those
    must be provided by the service consumer.
- ****Sabanciuniv VPN is required for out of campus connections.****

## Get All News Categories

**Address:**
[http://10.3.0.14:8080/newsapp/getallnewscategories](http://10.3.0.14:8080/newsapp/getallnewscategories)

**Returns:** The news categories in items field. All items contain id and name fields. You can use it to
check out filtering by category id using “Get News By Category Id” service.

## Get All News

**Address:**
[http://10.3.0.14:8080/newsapp/getall](http://10.3.0.14:8080/newsapp/getall)

**Returns:** The news items in “items” field. All items contain id, title, text, date (timestamp),
categoryName and image fields.

## Get News By Category Id

**Address:**
[http://10.3.0.14:8080/newsapp/getbycategoryid/{category](http://10.3.0.14:8080/newsapp/getbycategoryid/{category) id}

**Returns:** The news items in “items” field by the specified category. All items contain id, title, text,
date (timestamp), categoryName and image fields.

## Get News By Id

**Address:**
[http://10.3.0.14:8080/newsapp/getnewsbyid/{news](http://10.3.0.14:8080/newsapp/getnewsbyid/{news) id}

**Returns:** A single news item in the items field of a specific id. The item in the items list contains id,
title, text, date (timestamp), categoryName and image fields.


## Get Comments By News Id

**Address:**
[http://10.3.0.14:8080/newsapp/getcommentsbynewsid/{news](http://10.3.0.14:8080/newsapp/getcommentsbynewsid/{news) id}

**Returns:** All comments of the specified news id in items field. All items contain id, news_id, text and
name fields. Name field is of the comment owner’s.

## Post Comment

**Address:**
[http://](http://) 10 .3.0.14:8080/newsapp/savecomment

**Requires POST method, accepts JSON with the following format:**

{
“name”:”Henry Williams”,
“text”:”This is a comment text”,
“news_id”:”49”
}

**name** : Name of the comment owner
**text** : The comment text
**newsid** : id of the news the comment is posted to, must be a valid news id for a successful post, JSON
type is string.

All fields are required, if there is a missing field or an error you will get “0” in the
serviceMessageCode. If a successful post occures, you will get “1” in the serviceMessageCode field.
