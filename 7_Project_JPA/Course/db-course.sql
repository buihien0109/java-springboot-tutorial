-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: db
-- Thời gian đã tạo: Th7 25, 2022 lúc 07:36 AM
-- Phiên bản máy phục vụ: 8.0.29
-- Phiên bản PHP: 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `db-course`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `course`
--

CREATE TABLE `course` (
  `id` int NOT NULL,
  `description` text,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `supporter_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `course`
--

INSERT INTO `course` (`id`, `description`, `name`, `slug`, `thumbnail`, `type`, `supporter_id`) VALUES
(1, 'Commodi earum iusto voluptas ullam recusandae officia quibusdam rem debitis aut labore iste ut quis nostrum neque debitis repellat similique in nemo iusto minima odit ex dolore temporibus voluptatem dolorum ducimus ut magni itaque aspernatur iusto ipsam assumenda itaque qui quia suscipit sed sit harum debitis sunt eius id aut deserunt et harum quo velit quas illum rerum maxime et inventore fugit quam temporibus dolor placeat aut nobis sequi delectus facere occaecati aliquam dolor nisi sequi blanditiis aut et nihil velit dolorem totam blanditiis praesentium rerum nesciunt voluptatem aspernatur mollitia expedita repellat in porro sequi molestias et labore totam earum rerum at pariatur occaecati.', 'Barton-Kilback', 'barton-kilback', 'https://pigment.github.io/fake-logos/logos/medium/color/13.png', 'onlab', 3),
(2, 'Optio distinctio recusandae eos occaecati numquam qui eaque rerum omnis enim officiis consequatur provident dignissimos vitae vitae consequatur quam suscipit iste nam a qui odio maiores nihil impedit inventore id culpa deleniti delectus impedit libero exercitationem accusamus et molestiae consectetur et eveniet est saepe iure consequatur voluptas delectus placeat facilis ut et in omnis voluptatem vel voluptatem officiis dolorem enim et architecto quis odit vel qui expedita est labore possimus tempora aut porro deserunt doloribus vero id rem porro consequatur ipsa quas et eum blanditiis veritatis voluptates harum inventore magnam ipsum aliquam consectetur harum doloremque nemo soluta beatae unde ducimus et eaque.', 'Ratke Inc', 'ratke-inc', 'https://pigment.github.io/fake-logos/logos/medium/color/13.png', 'onlab', 3),
(3, 'Quae et itaque sit qui repudiandae ipsam quia fugiat corporis atque cumque blanditiis architecto deserunt sed excepturi maiores non odit omnis quaerat voluptates incidunt non omnis nihil voluptas dolores est repellat commodi magni id perspiciatis aut asperiores sit amet voluptatem molestiae tempora eaque exercitationem et quis facere eaque voluptatum minima tenetur incidunt veniam fugit consequatur minus inventore maiores dolorem sit asperiores nisi et pariatur vero dicta saepe quibusdam quas in et facilis nulla praesentium perferendis harum tempora atque animi iste qui neque molestiae non et perferendis quo amet illum odio vero consequatur ducimus velit natus repudiandae iusto expedita consectetur sit aut facilis.', 'Daniel Inc', 'daniel-inc', 'https://pigment.github.io/fake-logos/logos/medium/color/12.png', 'onlab', 3),
(4, 'Quia eius aut dolores et perspiciatis enim officia qui velit quia eius non eius iste aut sunt est qui ut quia aut debitis veritatis qui maxime dicta corporis cupiditate aperiam aut soluta labore culpa itaque voluptate dolorum distinctio voluptas sapiente laboriosam architecto vitae quas perspiciatis perferendis labore odio maxime et vero dolor pariatur maiores iure aspernatur temporibus dolorum ipsa vel qui itaque voluptatem sequi eos dolorum deleniti similique rerum voluptatem sit tempora ex totam illum ut similique ea eveniet illo debitis aut enim aliquid sint aut provident sunt maiores est est voluptate quae eos id quos alias qui velit labore recusandae.', 'Cremin Inc', 'cremin-inc', 'https://pigment.github.io/fake-logos/logos/medium/color/12.png', 'online', 2),
(5, 'Quidem laudantium amet dolorem magni sint vel alias possimus distinctio sit aperiam sed nesciunt minus at consequatur cumque eum quos optio voluptatem fugiat enim ullam omnis accusamus cum eveniet natus fugiat quam facilis facilis enim tempora quae corporis officiis ut tenetur illum quisquam sit autem nam eos ipsa corporis enim quia provident occaecati temporibus et est quis esse fugit perferendis officiis illo ut suscipit veniam magnam impedit sapiente numquam ducimus ut nisi cupiditate enim id repudiandae ut sint est cum ut vel non fugiat expedita amet ea minus voluptatibus reiciendis iste sequi rerum rem autem architecto necessitatibus autem corrupti eaque numquam ipsum est.', 'Muller-Bednar', 'muller-bednar', 'https://pigment.github.io/fake-logos/logos/medium/color/1.png', 'onlab', 2),
(6, 'Delectus fugit ut at error modi ut qui esse tenetur iusto accusantium sequi tempore maxime laborum eos est quaerat facilis non et sit nulla eum doloribus in repellat omnis assumenda voluptate quibusdam excepturi omnis libero cupiditate amet eos repudiandae aut rem et ab ex sunt unde voluptatibus nostrum delectus voluptatum possimus tempora nulla ipsum est possimus unde voluptas velit id alias nulla voluptas voluptatem omnis iusto ipsum voluptate pariatur illo in laboriosam quis in dignissimos voluptatem iusto labore quaerat corrupti tenetur aliquam odit vel similique aspernatur non omnis ad id laborum perferendis nesciunt dignissimos maiores officia eum est et eum in blanditiis minima at.', 'Ernser-Witting', 'ernser-witting', 'https://pigment.github.io/fake-logos/logos/medium/color/2.png', 'online', 3),
(7, 'Nulla et sunt ut ipsa eum sint harum saepe vitae similique ut totam vel ducimus quaerat vero hic porro eum odio eveniet voluptatem mollitia veniam rem officiis explicabo error voluptatum cum quos corporis blanditiis non molestiae adipisci ut consequuntur consequatur nostrum veritatis similique porro dolores totam sint unde et sit ipsum et eligendi est et debitis sint est autem ipsum velit quidem consequatur pariatur hic cupiditate ut dolor quis dolorem ipsam laboriosam optio sed cupiditate voluptatem dolor saepe hic ducimus expedita facere perferendis nemo ipsam natus iusto sunt expedita necessitatibus id omnis nemo velit necessitatibus harum sit ut et quia non at.', 'Keeling, Emard and Ferry', 'keeling-emard-and-ferry', 'https://pigment.github.io/fake-logos/logos/medium/color/2.png', 'online', 1),
(8, 'Nam ut aut id veniam ut asperiores velit et id in libero labore vel sunt consequuntur vitae sunt dignissimos et voluptate rerum ut rerum quia libero deserunt quae dolores doloremque rerum est doloribus eaque sit corporis consectetur nihil tenetur voluptatem et est magnam et aut quibusdam voluptatem a totam aut eos dolorem quasi laborum qui qui velit pariatur iure inventore suscipit optio et voluptatum deleniti ut eius enim et quod rerum et quo sed sunt eaque voluptatem iste esse autem illo non atque amet perspiciatis maxime in velit veniam sit officiis et omnis aut cum sit officia dolor dolores repudiandae sequi.', 'Macejkovic Inc', 'macejkovic-inc', 'https://pigment.github.io/fake-logos/logos/medium/color/6.png', 'online', 1),
(9, 'Possimus voluptatibus illo et cum nobis reiciendis dicta fugiat esse rem a et hic tempore unde dolorem ut nam enim qui voluptas minus explicabo sunt blanditiis accusantium et quisquam non perferendis et totam ut eos optio quis animi voluptatum optio voluptatibus vel doloribus ut sequi impedit qui quas hic rerum corrupti doloremque occaecati labore est dolor enim asperiores ut nesciunt sapiente ab quia eum autem dolores perferendis cupiditate maxime in eos consequatur aut nemo magnam ipsam omnis pariatur ut deleniti consequatur distinctio et numquam illum quia quas voluptas sunt saepe similique officia et quisquam sed recusandae facere consectetur omnis aut repudiandae odio non et recusandae.', 'Powlowski Inc', 'powlowski-inc', 'https://pigment.github.io/fake-logos/logos/medium/color/11.png', 'onlab', 1),
(10, 'Tenetur quae aut est beatae itaque et labore veritatis occaecati omnis itaque vel porro error magni reiciendis corporis qui minus quidem aut nisi dolore delectus corporis praesentium et unde adipisci veritatis rerum et aut expedita et aperiam et error quo dicta libero tempora eligendi et incidunt aut molestiae hic et inventore cupiditate dignissimos minus delectus cum qui vel cumque aut et suscipit molestiae non nesciunt quae architecto facilis deleniti ipsum ut sapiente qui officiis quidem odio autem molestiae eius natus provident voluptatem accusamus amet animi maiores neque ut voluptates nihil ut magnam at et et iusto reiciendis ab dolores ut corporis corrupti facere.', 'Lebsack Inc', 'lebsack-inc', 'https://pigment.github.io/fake-logos/logos/medium/color/3.png', 'onlab', 3),
(11, 'Sint totam corporis soluta sed blanditiis sint neque laboriosam quae et vel adipisci harum consequatur et qui cupiditate ex doloribus qui veritatis qui recusandae cumque atque vitae dolorum vitae alias ea corrupti voluptatem quia consequatur veritatis quod est soluta et rem architecto deleniti voluptas suscipit tempora et qui at id enim rerum tenetur itaque pariatur voluptatum consequatur eum ut ullam nihil et fugiat ad ipsa a et voluptate libero adipisci sunt fuga molestiae sed possimus autem maiores eos ratione officia labore recusandae doloremque aut iure quos sed similique quis expedita incidunt doloremque ut qui dolores est non iure et recusandae dolorum.', 'Leannon, Upton and Schaden', 'leannon-upton-and-schaden', 'https://pigment.github.io/fake-logos/logos/medium/color/6.png', 'onlab', 2),
(12, 'Repellendus perspiciatis aspernatur qui iste magni qui sapiente ducimus perferendis cum quo nesciunt iure quia quia aut dolorum id et nobis vel sequi doloribus necessitatibus maxime dolores id distinctio quaerat consequatur ut rerum eligendi facere dolorem eos possimus unde illum placeat delectus explicabo quia cupiditate quasi voluptatem et modi numquam error tempore eum recusandae et ab enim et aut aut reprehenderit vero sit incidunt animi sed et dolorem qui inventore eius rerum voluptatem eligendi quae adipisci aut maiores sed ducimus nostrum velit iure rem architecto est voluptatem dicta voluptate incidunt assumenda hic quis aut qui distinctio temporibus est magnam molestiae quas nobis quas.', 'Sawayn, Kiehn and Morar', 'sawayn-kiehn-and-morar', 'https://pigment.github.io/fake-logos/logos/medium/color/10.png', 'onlab', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `course_topic`
--

CREATE TABLE `course_topic` (
  `course_id` int NOT NULL,
  `topic_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `course_topic`
--

INSERT INTO `course_topic` (`course_id`, `topic_id`) VALUES
(3, 1),
(5, 1),
(10, 1),
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(10, 2),
(12, 2),
(1, 3),
(2, 3),
(4, 3),
(7, 3),
(8, 3),
(9, 3),
(11, 3),
(12, 3),
(4, 4),
(5, 4),
(6, 4),
(9, 4),
(11, 4),
(12, 4),
(2, 5),
(6, 5),
(8, 5),
(1, 6),
(5, 6),
(6, 6),
(7, 6),
(8, 6),
(9, 6),
(10, 6);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `image`
--

CREATE TABLE `image` (
  `id` varchar(255) NOT NULL,
  `link` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `image`
--

INSERT INTO `image` (`id`, `link`) VALUES
('0017f148-1bec-479b-b539-8b74a287ca65', 'https://pigment.github.io/fake-logos/logos/medium/color/11.png'),
('0b4748b6-4865-49c0-ab41-51710c1c6e39', 'https://pigment.github.io/fake-logos/logos/medium/color/12.png'),
('10e6fb98-9c1a-4168-9dae-5a8767f3f652', 'https://pigment.github.io/fake-logos/logos/medium/color/2.png'),
('11d5d852-8ee7-47a4-8205-e30ded74db65', 'https://pigment.github.io/fake-logos/logos/medium/color/1.png'),
('14fbcb0c-7598-48f8-9413-ad5887048333', 'https://pigment.github.io/fake-logos/logos/medium/color/3.png'),
('207d6455-b0b6-47fd-93a2-c628bd02ca3a', 'https://pigment.github.io/fake-logos/logos/medium/color/8.png'),
('25086edd-ca81-401a-b5e1-254553af1543', 'https://pigment.github.io/fake-logos/logos/medium/color/5.png'),
('3046d439-3aee-4825-a4f4-122725b06e73', 'https://pigment.github.io/fake-logos/logos/medium/color/11.png'),
('42161c17-6190-4713-9ee4-3630a76cce2e', 'https://pigment.github.io/fake-logos/logos/medium/color/5.png'),
('47b3f917-4533-4c5a-b806-70374ab66a78', 'https://pigment.github.io/fake-logos/logos/medium/color/13.png'),
('4a1fe1ff-75ce-4b90-971d-805f42f2bac4', 'https://pigment.github.io/fake-logos/logos/medium/color/10.png'),
('51fe846f-0363-4799-9921-6889bd094873', 'https://pigment.github.io/fake-logos/logos/medium/color/10.png'),
('62d21e75-33c3-47f6-8dd3-630cf269e827', 'https://pigment.github.io/fake-logos/logos/medium/color/4.png'),
('73c64d64-091b-4ce9-be08-63baf1b1f63c', 'https://pigment.github.io/fake-logos/logos/medium/color/8.png'),
('795e43e1-0ae3-4754-93d8-5acf409acdba', 'https://pigment.github.io/fake-logos/logos/medium/color/1.png'),
('87dfabdb-227a-4d98-933a-5856b033e9cf', 'https://pigment.github.io/fake-logos/logos/medium/color/5.png'),
('8c74ad02-8928-4840-81c5-3ecd5f7922b9', 'https://pigment.github.io/fake-logos/logos/medium/color/5.png'),
('94d56551-eb84-4667-8b4f-33749a478396', 'https://pigment.github.io/fake-logos/logos/medium/color/12.png'),
('9eb4efad-621a-4a93-ba15-776907f72b29', 'https://pigment.github.io/fake-logos/logos/medium/color/2.png'),
('b1d5a8da-fd9f-4ebd-a832-64f02c79dbf1', 'https://pigment.github.io/fake-logos/logos/medium/color/3.png'),
('ba31b13c-30bc-4352-8b2b-6995c448cb83', 'https://pigment.github.io/fake-logos/logos/medium/color/13.png'),
('c2bd0921-9c9d-49cc-892c-ed282172ec10', 'https://pigment.github.io/fake-logos/logos/medium/color/2.png'),
('c42d4395-70d1-4d27-b4f0-94e6cda922b8', 'https://pigment.github.io/fake-logos/logos/medium/color/9.png'),
('c7e4298a-ceb9-4b12-854c-4038326e8a75', 'https://pigment.github.io/fake-logos/logos/medium/color/8.png'),
('c9584dbf-b1f6-4ea7-8dcd-957b90e20f14', 'https://pigment.github.io/fake-logos/logos/medium/color/2.png'),
('cbfea9b2-8454-4b73-bc9a-1e560f217c76', 'https://pigment.github.io/fake-logos/logos/medium/color/6.png'),
('d8355e7a-96f0-4f85-8247-2a9b530aaaf3', 'https://pigment.github.io/fake-logos/logos/medium/color/11.png'),
('ec284988-f154-4d78-8954-2e347842b0dd', 'https://pigment.github.io/fake-logos/logos/medium/color/1.png'),
('efc81404-60c9-4d68-a492-4ea3f4514241', 'https://pigment.github.io/fake-logos/logos/medium/color/1.png'),
('f13451b1-33d7-4ed1-b0e9-818d5058eec2', 'https://pigment.github.io/fake-logos/logos/medium/color/10.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `topic`
--

CREATE TABLE `topic` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `topic`
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
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `avatar`, `email`, `name`, `phone`) VALUES
(1, 'https://media.techmaster.vn/api/static/crop/bv9jp4k51co7nj2mhht0', 'manpham@techmaster.vn', 'Phạm Thị Mẫn', '0963023185'),
(2, 'https://media.techmaster.vn/api/static/c2m5ou451cob24f6skeg/ccjlg0NC', 'thinh@techmaster.vn', 'Dương Đức Thịnh', '0987273764'),
(3, 'https://media.techmaster.vn/api/static/crop/brm3huc51co50mv77sag', 'huong@techmaster.vn', 'Nguyễn Thanh Hương', '0382416368');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtp8ad5if67gg9152y2ijo7mnf` (`supporter_id`);

--
-- Chỉ mục cho bảng `course_topic`
--
ALTER TABLE `course_topic`
  ADD PRIMARY KEY (`course_id`,`topic_id`),
  ADD KEY `FKpmlbmev283ud50kxgvmyjcbhj` (`topic_id`);

--
-- Chỉ mục cho bảng `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `topic`
--
ALTER TABLE `topic`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `course`
--
ALTER TABLE `course`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `topic`
--
ALTER TABLE `topic`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `FKtp8ad5if67gg9152y2ijo7mnf` FOREIGN KEY (`supporter_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `course_topic`
--
ALTER TABLE `course_topic`
  ADD CONSTRAINT `FK6esfb41t5ja5jp8p49uv72x9d` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  ADD CONSTRAINT `FKpmlbmev283ud50kxgvmyjcbhj` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
