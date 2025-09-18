INSERT INTO tb_usuario (id_usuario, ds_email, role_usuario, ds_senha) VALUES
(1, 'usuario@email.com', 1, '$2a$10$AI3vjykqoZPuRSJLvl00zOJtLYC2TvikDGgKhvhRdPAHvLyu/TFce'),
(2, 'admin@email.com', 0, '$2a$10$5UX8Et.YepfpbLLqzkOUZ.YtJtBwffmuajtRg7hjIXkVyp2NX.tPS');

INSERT INTO tb_produto (id_produto, nm_produto, ds_produto, vl_produto) VALUES
(1, 'Camisa Nike', 'Camisa básica Nike branca', 129.90),
(2, 'Calça Adidas', 'Calça Adidas preta', 149.90),
(3, 'Tênis corrida', 'Tênis de corrida preto', 259.90);

INSERT INTO tb_pedido (data_pedido, id_pedido, id_usuario, total_pedido) VALUES
('18-09-2025', 1, 1, 389.80),
('15-06-2025', 2, 1, 129.90),
('20-07-2025', 3, 2, 259.90);

INSERT INTO tb_pedido_produto (id_pedido, id_produto) VALUES
(1, 1),
(1, 2),
(2, 1),
(3, 3);