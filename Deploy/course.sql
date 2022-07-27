-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Jul 27, 2022 at 08:13 AM
-- Server version: 8.0.29
-- PHP Version: 8.0.19

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `heroku_6d8a39d2f346ed6`
--
CREATE DATABASE IF NOT EXISTS `heroku_6d8a39d2f346ed6` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `heroku_6d8a39d2f346ed6`;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int NOT NULL,
  `description` text,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `supporter_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `description`, `name`, `slug`, `thumbnail`, `type`, `supporter_id`) VALUES
(1, 'Voluptatem deserunt quia magni officiis vel ipsum dolor commodi minima odit dolorem consequatur asperiores temporibus aspernatur sint consectetur non molestias maxime nulla consequatur quia et atque ut enim et aut ut nulla et consequuntur voluptas eveniet dolorem illum veritatis maxime quod esse ipsa incidunt laudantium at molestias ut magni facilis facilis aut adipisci ullam rerum iste amet ex eveniet omnis sit ea consequatur non sequi odit ullam omnis aut debitis et hic architecto repellendus ea accusantium rem iusto dolores id et dolorem distinctio voluptatem ut exercitationem et rem velit expedita est minus aspernatur molestias qui ducimus aut voluptatem rerum consequatur iusto unde sed dolorem.', 'Cormier-Wyman', 'cormier-wyman', 'https://pigment.github.io/fake-logos/logos/medium/color/9.png', 'online', 1),
(2, 'Excepturi quia dolor et quas similique ex ut dignissimos libero atque velit ea nihil molestiae eos est et repudiandae iure quam facere quo sit aut voluptas aut iste consequatur corporis veritatis doloremque doloremque sit reprehenderit eum ullam exercitationem odit maiores delectus delectus aperiam eaque fugit vel ut aperiam at totam perferendis eligendi esse cupiditate sed ea molestiae aut ipsa neque illo incidunt nobis sapiente voluptatem beatae sit et enim molestias voluptatibus sed impedit ipsum veritatis neque eaque optio et excepturi libero ad sit aut vel harum dignissimos officia totam qui eos illum et dolorem nisi dolor vitae consectetur vel nihil autem et minus asperiores neque.', 'Hand-Bauch', 'hand-bauch', 'https://pigment.github.io/fake-logos/logos/medium/color/6.png', 'onlab', 3),
(3, 'A laborum unde earum qui odio est dolorem velit ut odio et ut occaecati culpa quaerat laudantium autem nulla voluptatem voluptas accusantium consequatur et quia in veritatis qui harum minima sint beatae sunt voluptatibus molestias voluptatibus laudantium eum alias temporibus corrupti possimus ipsam debitis temporibus saepe nulla ad minus deserunt qui consequuntur ipsam qui aut magnam odit qui ipsa enim et minus dolor labore accusantium aliquam accusantium qui eveniet quis veniam ea inventore fugiat iste quisquam ipsa quasi minus ducimus voluptates nihil dolorem qui odit cum magni corrupti voluptatem alias et in rerum et pariatur ex autem quasi reprehenderit ex ut laudantium voluptatem provident.', 'Boehm Group', 'boehm-group', 'https://pigment.github.io/fake-logos/logos/medium/color/2.png', 'onlab', 1),
(4, 'Deleniti consectetur ratione officia aut aut ut earum ducimus ut velit odio corrupti voluptatem et explicabo nulla fugit nihil asperiores veniam harum et voluptatum iure quasi et praesentium ipsum dolores dolor ut quia odio commodi qui soluta illum doloremque occaecati alias saepe aut nostrum quia dolorem ipsum impedit laudantium ut sint voluptatem quaerat ut sunt odio expedita repudiandae ab repudiandae et eligendi ea voluptatem occaecati pariatur quod omnis reiciendis optio tempore et occaecati aut fuga dicta fugiat accusantium sunt natus unde velit qui sunt quia nostrum atque ea hic non qui omnis accusamus et porro praesentium suscipit reprehenderit itaque in vel dolore et.', 'Reinger-Weimann', 'reinger-weimann', 'https://pigment.github.io/fake-logos/logos/medium/color/2.png', 'online', 1),
(5, 'Tempora laborum fugiat laboriosam quisquam id ullam voluptatibus minima et ullam cumque tempore totam ullam omnis nihil aut quidem et autem aut omnis quia et veritatis sed delectus consequatur illo laboriosam aliquid autem iure numquam porro est magni beatae aut veniam similique saepe expedita iste officia porro est fugit aspernatur voluptatibus molestiae sit ut optio sit adipisci ut et voluptatem id necessitatibus alias libero dignissimos aliquam architecto nisi ipsa sed eos voluptatem fugiat ea ut qui et unde pariatur at aut a nesciunt assumenda maiores et et amet omnis officiis eos et ut et et animi eius rerum recusandae omnis nisi iure.', 'Baumbach-Hansen', 'baumbach-hansen', 'https://pigment.github.io/fake-logos/logos/medium/color/13.png', 'onlab', 3),
(6, 'Sunt exercitationem hic iure ipsa hic quo eligendi enim odit voluptatem sit quia qui et modi qui ipsam odio et aliquid dignissimos nulla velit aut quasi quod quaerat quis perferendis unde enim labore aut atque repellat impedit quae sed est culpa nulla dolorem veniam ex et nihil sint ipsam totam deleniti fuga quae molestias amet et minima et perspiciatis vel corporis doloribus tenetur et similique dignissimos inventore qui aliquid sint repellendus expedita pariatur assumenda modi voluptas molestias voluptatum molestiae nemo quos et dignissimos ab aliquam voluptatem hic inventore perferendis recusandae voluptate reiciendis voluptatem qui saepe eos eos est quia et quidem quis.', 'Krajcik, Parisian and Cummerata', 'krajcik-parisian-and-cummerata', 'https://pigment.github.io/fake-logos/logos/medium/color/4.png', 'onlab', 3),
(7, 'Vel consequatur inventore dignissimos pariatur debitis ipsa rem blanditiis tempore cumque pariatur culpa rem dolores aut distinctio et quo commodi placeat qui aperiam voluptas nihil similique vel nemo commodi neque molestiae harum dolor minima cupiditate distinctio libero voluptatibus asperiores et voluptas reiciendis ipsa mollitia blanditiis maiores aut iusto explicabo reiciendis sequi deserunt quia quia occaecati vel maxime sequi quia sed amet sit ut perferendis suscipit dolor qui est nobis molestiae natus porro ad qui magni numquam id laborum distinctio sequi itaque architecto id consectetur voluptas ea nesciunt molestiae nihil repellendus labore corporis optio sit et quae rerum error et atque optio quo eligendi.', 'McGlynn, Rowe and Hickle', 'mcglynn-rowe-and-hickle', 'https://pigment.github.io/fake-logos/logos/medium/color/9.png', 'onlab', 1),
(8, 'Commodi fuga qui exercitationem earum necessitatibus voluptatem sed accusantium et eligendi facilis numquam et et incidunt eum voluptatem placeat eligendi dolor mollitia nulla cumque sed et exercitationem reiciendis ipsum totam et sit ea provident ea necessitatibus at voluptas voluptas et dolore esse voluptatibus molestiae voluptate fugiat ratione consequatur qui aliquid nostrum aut odio dicta praesentium ut vel qui hic maiores dolores dolore reprehenderit quos cupiditate occaecati non quo et cupiditate deleniti aliquam unde sequi quo debitis cumque ut sequi alias labore eum enim quasi sed et nisi voluptatibus commodi incidunt harum ut voluptatem voluptates nobis minus necessitatibus quis sit et consequuntur quasi similique dolores reiciendis.', 'Ratke-Conroy', 'ratke-conroy', 'https://pigment.github.io/fake-logos/logos/medium/color/11.png', 'onlab', 2),
(9, 'Eos dolorum necessitatibus voluptatibus officiis debitis vel ullam consequatur nihil nemo deleniti natus laborum voluptas ut eaque sunt id reprehenderit dolorem debitis aliquid qui non vel nobis labore voluptate quia et aut aut sequi rerum et eos sit error sit velit et deserunt autem in dicta fuga rerum ipsam eum soluta et facilis autem et non commodi commodi corrupti et tenetur velit placeat soluta cumque necessitatibus aut optio nihil provident ipsum sed fuga minus aut sunt quod qui voluptatibus similique pariatur et iure et et sit aut cumque sapiente quae molestiae in qui cum accusantium officiis adipisci accusantium eius qui ab.', 'Kuhn, Dickinson and Harvey', 'kuhn-dickinson-and-harvey', 'https://pigment.github.io/fake-logos/logos/medium/color/13.png', 'online', 2),
(10, 'Fuga quo at omnis id voluptatum inventore dolorum aut quod aut iure non a quia rerum explicabo vitae illo possimus illo exercitationem occaecati officia ex optio aut et delectus incidunt maiores fugiat et eum suscipit voluptatum eum eaque ipsum odit qui animi iusto blanditiis est dignissimos earum doloremque doloremque quaerat ad voluptatibus non debitis ipsum ut repellat eos amet eos ducimus ut atque ullam debitis minus voluptate voluptatem unde a enim omnis ad corrupti facilis quis delectus qui error est sunt fugit assumenda eligendi aspernatur deserunt corrupti necessitatibus eligendi ipsum delectus ea voluptatem quis et excepturi sit ut pariatur eaque nobis voluptatem quaerat laboriosam veniam.', 'Satterfield-Ondricka', 'satterfield-ondricka', 'https://pigment.github.io/fake-logos/logos/medium/color/10.png', 'online', 2),
(11, 'Sit voluptatibus voluptas possimus non sit omnis ea ducimus nam quia ipsa iusto aliquid sed aut natus et voluptas officia et porro enim consequatur at reprehenderit error magnam odio ea nostrum sint impedit amet eaque quod aperiam fugiat sunt velit vel occaecati aut explicabo excepturi quae itaque fugit odio dignissimos dicta consequatur aliquam maxime mollitia saepe quam ea quia et dicta nemo dolores quia fugiat omnis fuga quam consequatur earum velit repellendus necessitatibus et quis aut itaque iusto consequatur praesentium nobis perspiciatis occaecati dolorem quo aut quam doloremque ipsa doloremque quod ratione quidem et quos est repellendus voluptatem nulla ab quam.', 'Kassulke-Miller', 'kassulke-miller', 'https://pigment.github.io/fake-logos/logos/medium/color/4.png', 'onlab', 3),
(12, 'Repellendus consectetur voluptate ullam ad sequi non cumque sit aliquam magni nostrum nisi fuga recusandae ea error dolor architecto exercitationem ab voluptatem et eos saepe aut quibusdam sunt voluptatem saepe quia ipsam corrupti inventore qui sequi sit sit voluptas sed quis molestiae aspernatur cupiditate omnis sed nulla natus provident animi sed praesentium dolor recusandae eos nisi nemo enim illum delectus doloremque dolorem voluptatem omnis id praesentium iusto dolor aut et earum quae vitae repellendus molestiae aperiam facilis ipsa impedit et aut sit aliquid voluptatem veritatis accusamus aspernatur odio nisi dolorem et quisquam consequuntur hic odit ut molestiae asperiores et quibusdam nobis recusandae qui culpa.', 'Braun, Hickle and Hessel', 'braun-hickle-and-hessel', 'https://pigment.github.io/fake-logos/logos/medium/color/7.png', 'onlab', 1);

-- --------------------------------------------------------

--
-- Table structure for table `course_topic`
--

DROP TABLE IF EXISTS `course_topic`;
CREATE TABLE `course_topic` (
  `course_id` int NOT NULL,
  `topic_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `course_topic`
--

INSERT INTO `course_topic` (`course_id`, `topic_id`) VALUES
(1, 1),
(4, 1),
(7, 1),
(9, 1),
(10, 1),
(12, 1),
(1, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(8, 2),
(10, 2),
(11, 2),
(1, 3),
(2, 3),
(3, 3),
(5, 3),
(7, 3),
(8, 3),
(9, 3),
(2, 4),
(4, 4),
(6, 4),
(8, 4),
(12, 4),
(3, 5),
(7, 5),
(10, 5),
(11, 5),
(12, 5),
(6, 6),
(11, 6);

-- --------------------------------------------------------

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` varchar(255) NOT NULL,
  `link` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `image`
--

INSERT INTO `image` (`id`, `link`) VALUES
('0256b4ba-2510-4733-baa4-cb43cfe04447', 'https://pigment.github.io/fake-logos/logos/medium/color/12.png'),
('0ffa3366-4040-45de-b41c-12e7724f2c62', 'https://pigment.github.io/fake-logos/logos/medium/color/13.png'),
('1d0966ed-6439-4919-ae03-564f040071e0', 'https://pigment.github.io/fake-logos/logos/medium/color/7.png'),
('23c92375-9fcf-4e35-a3b3-324a0f8e4f4e', 'https://pigment.github.io/fake-logos/logos/medium/color/2.png'),
('34a82d58-4e9d-4e7e-bcf6-3a24731f61dc', 'https://pigment.github.io/fake-logos/logos/medium/color/11.png'),
('3dea4492-a7b8-48d5-8de7-2127a5817e8b', 'https://pigment.github.io/fake-logos/logos/medium/color/2.png'),
('44be4dbe-2d33-4294-94b2-d99be678b15c', 'https://pigment.github.io/fake-logos/logos/medium/color/2.png'),
('52fe2f45-ee60-4370-bcc2-96209f8e46aa', 'https://pigment.github.io/fake-logos/logos/medium/color/7.png'),
('531be18e-bcd9-45f1-9019-6fcf5e00a9e1', 'https://pigment.github.io/fake-logos/logos/medium/color/12.png'),
('5760ba1a-de38-46c3-838d-d8ed6515df39', 'https://pigment.github.io/fake-logos/logos/medium/color/3.png'),
('78d7a226-cffd-42cb-a22d-a1f8019bd537', 'https://pigment.github.io/fake-logos/logos/medium/color/9.png'),
('891e86ba-9e1f-4cfc-8438-55e80fcb176b', 'https://pigment.github.io/fake-logos/logos/medium/color/6.png'),
('934588fa-9801-4efa-ba4f-41c702db514f', 'https://pigment.github.io/fake-logos/logos/medium/color/10.png'),
('93cc1f0a-008c-4cc1-b37a-6fddbde0da6a', 'https://pigment.github.io/fake-logos/logos/medium/color/10.png'),
('9461ffc1-f613-4c58-baaf-d788742e8837', 'https://pigment.github.io/fake-logos/logos/medium/color/4.png'),
('98f2ac70-1353-4689-bc52-26b36d00a153', 'https://pigment.github.io/fake-logos/logos/medium/color/11.png'),
('9a354615-1beb-4477-8aeb-445da72bfd36', 'https://pigment.github.io/fake-logos/logos/medium/color/10.png'),
('b6e64803-9a72-4fbd-ab08-27bd5e972621', 'https://pigment.github.io/fake-logos/logos/medium/color/13.png'),
('b89b2255-5a19-43eb-adbf-e3dcae4cc846', 'https://pigment.github.io/fake-logos/logos/medium/color/5.png'),
('b8c27814-83b7-4ae1-848c-01ee782109bb', 'https://pigment.github.io/fake-logos/logos/medium/color/2.png'),
('bc8cf073-1504-4e4f-8a28-7beb8e771be7', 'https://pigment.github.io/fake-logos/logos/medium/color/11.png'),
('bd672f98-f806-478a-8d80-d17876e0f720', 'https://pigment.github.io/fake-logos/logos/medium/color/13.png'),
('bd8bd2ed-2412-4d4f-883a-606c5080da4e', 'https://pigment.github.io/fake-logos/logos/medium/color/7.png'),
('c4f31791-c97e-4cc8-8a11-9e6365ee7c92', 'https://pigment.github.io/fake-logos/logos/medium/color/7.png'),
('ca6d71b2-9e76-4cb6-abda-0aa007efd549', 'https://pigment.github.io/fake-logos/logos/medium/color/11.png'),
('d640f188-f2e3-48c2-bfa5-474e67974cef', 'https://pigment.github.io/fake-logos/logos/medium/color/6.png'),
('d7428f3f-f7aa-4c8c-b2a1-68eafd7ed5da', 'https://pigment.github.io/fake-logos/logos/medium/color/5.png'),
('dd57f044-d1fd-4b33-9be3-ae6374053974', 'https://pigment.github.io/fake-logos/logos/medium/color/2.png'),
('e8b6dbfe-06dc-4f92-94dd-b7ea2c2f8d03', 'https://pigment.github.io/fake-logos/logos/medium/color/10.png'),
('f0791bba-1fec-4ffa-b1b5-761ea6b78f56', 'https://pigment.github.io/fake-logos/logos/medium/color/9.png');

-- --------------------------------------------------------

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `topic`
--

INSERT INTO `topic` (`id`, `name`) VALUES
(1, 'AWS'),
(2, 'Devops'),
(3, 'BackEnd'),
(4, 'FrontEnd'),
(5, 'Database'),
(6, 'Mobile');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `avatar`, `email`, `name`, `phone`) VALUES
(1, 'https://media.techmaster.vn/api/static/crop/bv9jp4k51co7nj2mhht0', 'manpham@techmaster.vn', 'Phạm Thị Mẫn', '0963023185'),
(2, 'https://media.techmaster.vn/api/static/c2m5ou451cob24f6skeg/ccjlg0NC', 'thinh@techmaster.vn', 'Dương Đức Thịnh', '0987273764'),
(3, 'https://media.techmaster.vn/api/static/crop/brm3huc51co50mv77sag', 'huong@techmaster.vn', 'Nguyễn Thanh Hương', '0382416368');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtp8ad5if67gg9152y2ijo7mnf` (`supporter_id`);

--
-- Indexes for table `course_topic`
--
ALTER TABLE `course_topic`
  ADD PRIMARY KEY (`course_id`,`topic_id`),
  ADD KEY `FKpmlbmev283ud50kxgvmyjcbhj` (`topic_id`);

--
-- Indexes for table `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `topic`
--
ALTER TABLE `topic`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `topic`
--
ALTER TABLE `topic`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `FKtp8ad5if67gg9152y2ijo7mnf` FOREIGN KEY (`supporter_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `course_topic`
--
ALTER TABLE `course_topic`
  ADD CONSTRAINT `FK6esfb41t5ja5jp8p49uv72x9d` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  ADD CONSTRAINT `FKpmlbmev283ud50kxgvmyjcbhj` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
