-- MySQL schema placeholder
-- Database: cricket_db
CREATE DATABASE IF NOT EXISTS cricket_db;
USE cricket_db;

-- Table for teams
CREATE TABLE teams (
    team_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Table for matches
CREATE TABLE matches (
    match_id INT AUTO_INCREMENT PRIMARY KEY,
    team_a INT,
    team_b INT,
    overs INT,
    current_over INT DEFAULT 0,
    FOREIGN KEY (team_a) REFERENCES teams(team_id),
    FOREIGN KEY (team_b) REFERENCES teams(team_id)
);

-- Table for ball-by-ball scores
CREATE TABLE balls (
    ball_id INT AUTO_INCREMENT PRIMARY KEY,
    match_id INT,
    over_no INT,
    ball_no INT,
    runs INT,
    wicket BOOLEAN DEFAULT 0,
    event VARCHAR(20),   -- like "FOUR", "SIX", "WICKET", "RO"
    FOREIGN KEY (match_id) REFERENCES matches(match_id)
);
