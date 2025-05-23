import React, { useState } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

const Container = styled.div`
    background: #f6f5f7;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    width: 100vw;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
`;

const Wrapper = styled.div`
    background: #fff;
    border-radius: 10px;
    box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
    width: 700px;
    max-width: 90vw;
    min-height: 500px;
    display: flex;
    overflow: hidden;
`;

const LeftSide = styled.div`
    flex: 1;
    background: #ff4b2b;
    color: white;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 50px;
    font-weight: bold;
    font-size: 1.4rem;
`;

const FormContainer = styled.div`
    flex: 1;
    padding: 50px 40px;
    display: flex;
    flex-direction: column;
    justify-content: center;
`;

const Title = styled.h1`
    font-weight: bold;
    margin-bottom: 10px;
`;

const Subtitle = styled.span`
    font-size: 14px;
    color: #999;
    margin-bottom: 30px;
`;

const Input = styled.input`
    padding: 12px 15px;
    margin: 8px 0;
    width: 100%;
    border: 1px solid #ccc;
    border-radius: 20px;
    outline: none;
    font-size: 14px;
    transition: 0.3s border-color ease-in-out;
    &:focus {
        border-color: #ff4b2b;
    }
`;

const Button = styled.button`
    margin-top: 20px;
    border-radius: 20px;
    border: none;
    padding: 12px 45px;
    background-color: #ff4b2b;
    color: white;
    font-weight: bold;
    cursor: pointer;
    transition: 0.3s background-color ease-in-out;
    &:hover {
        background-color: #ff3a1a;
    }
`;

const SwitchText = styled.p`
    margin-top: 30px;
    font-size: 14px;
    color: #777;
    text-align: center;
`;

const LinkButton = styled.button`
    background: none;
    border: none;
    color: #ff4b2b;
    font-weight: bold;
    cursor: pointer;
    margin-left: 5px;
    text-decoration: underline;
    font-size: 14px;
`;

export default function RegisterPage() {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [name, setName] = useState('');
    const [password, setPassword] = useState('');

    const handleRegister = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/api/users/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, name, password }),
            });

            if (!response.ok) {
                const errorMsg = await response.text();
                alert('회원가입 실패: ' + errorMsg);
                return;
            }

            alert('회원가입이 완료되었습니다. 로그인 페이지로 이동합니다.');
            navigate('/login');
        } catch (error) {
            alert('회원가입 중 오류가 발생했습니다.');
            console.error(error);
        }
    };

    return (
        <Container>
            <Wrapper>
                <LeftSide>
                    <h1>Hello, Friend</h1>
                    <p>Enter your personal details and start journey with us</p>
                    <Button onClick={() => navigate('/login')}>Sign In</Button>
                </LeftSide>
                <FormContainer>
                    <Title>Sign Up</Title>
                    <Subtitle>or use your email for registration</Subtitle>
                    <form onSubmit={handleRegister}>
                        <Input
                            type="email"
                            placeholder="Email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                        <Input
                            type="text"
                            placeholder="Name"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            required
                        />
                        <Input
                            type="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                        <Button type="submit">Sign Up</Button>
                    </form>
                    <SwitchText>
                        Already have an account?
                        <LinkButton onClick={() => navigate('/login')}>Sign In</LinkButton>
                    </SwitchText>
                </FormContainer>
            </Wrapper>
        </Container>
    );
}
