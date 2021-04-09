package tweettest;

import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.UUID;

public class TweetAPIClientTest {
    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();
    }

    @Test
    public void testUserCanTweetSuccessfully() {

        // User sent a tweet
        String tweet = "We are learning Rest API using Rest Assured and our First Tweet"+UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        // Verify that the tweet is successful
        response.statusCode(200);
        // Verity tweet value
        String actualTweet=response.extract().body().path("text");
        // Long id= response.extract().body().path("id");
        //System.out.println(id);
        Assert.assertEquals(actualTweet,tweet,"Tweet does not match");
    }

    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARoq() {
        // User sent a tweet
        String tweet = "We are learning Rest API using Rest Assured and our First same Tweet";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(403);
        // Verity tweet value

        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage="Status is a duplicate";
        String actualTweet=response.extract().body().path("errors[0].message");
//        String actualTweet=response.extract().body().path("text");
//        // Long id= response.extract().body().path("id");
//        //System.out.println(id);
        Assert.assertEquals(actualTweet,expectedMessage,"Tweet does not match");
    }
    @Test
    public void testDeleteTweet(){
        String tweet="This week is the last week of class";
        ValidatableResponse deleteResponse= this.tweetAPIClient.deleteTweet(1379475026999660548l);
        deleteResponse.statusCode(200);
        String actualTweet= deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);

    }
    @Test
    public void testCreateTweet(){
        String tweet="Creating tweet by myself";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(actualTweet,tweet,"Tweet does not match");
    }

    @Test
    public void testHomeTimeline(){
        ValidatableResponse response = this.tweetAPIClient.getHome_Timeline();
        System.out.println(response.extract().body().asPrettyString());
        String expectedTweet="Thu Apr 08 17:27:36 +0000 2021";
        String actualTweet=response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet,actualTweet,"Tweet does not match");
    }
//    @Test
//    public void testRetweetersEndpoint(){
//        ValidatableResponse response = this.tweetAPIClient.getRetweetersEndpoint();
//        System.out.println(response.extract().body().asPrettyString());
//        response.statusCode(200);
//    }

    @Test
    public void testFriendship(){
        String userName="Angelina";
        ValidatableResponse response= this.tweetAPIClient.createFriendship(userName);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(actualTweet,userName,"UserID does not match");
    }

    @Test
    public void testGetFriendshipLookup(){
        ValidatableResponse response = this.tweetAPIClient.getFriendshipLookup();
        System.out.println(response.extract().body().asPrettyString());
//        String expectedTweet="Thu Apr 08 17:27:36 +0000 2021";
//        String actualTweet=response.extract().body().path("errors[0].message");
//        Assert.assertEquals(actualTweet,actualTweet,"Tweet does not match");
    }
    @Test
    public void testCreateFavorite(){
        ValidatableResponse response = this.tweetAPIClient.postFavorite(1380210732030132226L);
        System.out.println(response.extract().body().asPrettyString());
        String expectedTweet = "id parameter is missing.";
        String actualTweet=response.extract().body().path("errors[0].message");
        Assert.assertEquals(expectedTweet,actualTweet);
    }
    @Test
    public void testDeleteFavorite(){
        ValidatableResponse deleteResponse= this.tweetAPIClient.deleteFavorite(1380365580868411400l);
        deleteResponse.statusCode(200);
//        String expectedTweet="";
//        String actualTweet= deleteResponse.extract().body().path("text");
//        Assert.assertEquals(tweet,actualTweet);

    }



}
