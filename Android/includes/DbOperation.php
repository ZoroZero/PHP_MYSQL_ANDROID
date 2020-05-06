<?php
    class DbOperator{
        private $con;

        function __contruct(){
            require_once dirname(__FILE__).'/DbConnector.php';

            $db = new DbConnector();

            $this->con = $db->connect();
        }

        // Register user
        function createUser($username, $password){
            if($this->userExist($username)){
                return 0;
            }
            else{
                $pass = md5($password);
                $stmt =$this->con->prepare("INSERT INTO `user_login` (`user_ID`, `username`, `password`) VALUES (NULL, ?, ?);");
                $stmt->bind_param("ss", $username, $pass);

                if($stmt->execute()){
                    return 1;
                }
                else
                    return 2;
            }
        }

        // Login user
        function userLogin($username, $password){
            $pass = md5($password);
            $stmt =$this->con->prepare("SELECT user_ID from user_login WHERE username = ? AND password = ?");
            $stmt->bind_param("ss", $username, $pass);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows > 0;
        }

        // Get user data from user name
        function getUserByUserName($username){
            $stmt =$this->con->prepare("SELECT * from user_login WHERE username = ? ");
            $stmt->bind_param("s", $username);
            $stmt->execute();
            return $stmt->get_result()->fetch_assoc();
        }
        
        // Check if user already exist in DB
        private function userExist($username){
            $stmt =$this->con->prepare("SELECT user_ID from user_login WHERE username = ? ");
            $stmt->bind_param("s", $username);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows > 0;
        }
    }